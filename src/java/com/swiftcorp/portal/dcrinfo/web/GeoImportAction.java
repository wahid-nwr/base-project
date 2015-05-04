package com.swiftcorp.portal.dcrinfo.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.dcrinfo.StrutsUploadObject;
import com.swiftcorp.portal.geo.dto.GeoImportHHRegDTO;
import com.swiftcorp.portal.geo.dto.SkDataDTO;
import com.swiftcorp.portal.geo.service.IGeoService;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.dto.SSDTO;

public class GeoImportAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( GeoImportAction.class );
	@SuppressWarnings("unused")
	private IGeoService geoService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setGeoService ( IGeoService geoService )
	{
		this.geoService = geoService;
	}
	
	public ActionForward importCSVOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "importGeoOperation() :" );
		DynaValidatorActionForm geoForm = (DynaValidatorActionForm) form;
		
		// Process the FormFile
		StrutsUploadObject strutsUploadObject = (StrutsUploadObject) geoForm.get ( "fileObject" );
		FormFile myFile = (FormFile) strutsUploadObject.getTheFile ();
		
		String contentType = myFile.getContentType ();
		String fileName = myFile.getFileName ();
		int fileSize = myFile.getFileSize ();
		byte[] fileData = myFile.getFileData ();
		System.out.println ( "contentType: " + contentType );
		System.out.println ( "File Name: " + fileName );
		System.out.println ( "File Size: " + fileSize );
		String filePath = request.getSession ().getServletContext ().getRealPath ( "/" ) + "upload";
		
		// create the upload folder if not exists
		File folder = new File ( filePath );
		if ( !folder.exists () )
		{
			folder.mkdir ();
		}
		
		if ( !("").equals ( fileName ) )
		{
			
			System.out.println ( "Server path:" + filePath );
			File newFile = new File ( filePath, fileName );
			
			if ( !newFile.exists () )
			{
				FileOutputStream fos = new FileOutputStream ( newFile );
				fos.write ( myFile.getFileData () );
				fos.flush ();
				fos.close ();
			}
			List<ArrayList<String>> rowDataList = this.readAny ( newFile );
			request.getSession().setAttribute("thefile", rowDataList);
		}
		
		String[][] messageArgValues =
		{
			{
				fileName
			}
		};
		
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return mapping.findForward ( ForwardNames.PROMPT_IMPORT );
	}
	
	public List<ArrayList<String>> readAny(File inputWorkBook ) throws IOException
	{
		Workbook w;
		List<String> columnDataList = new ArrayList<String>();
		List<ArrayList<String>> rowDataList = new ArrayList<ArrayList<String>>();
		String content = "";
		try
		{
			w = Workbook.getWorkbook ( inputWorkBook );
			for ( int k = 0; k < w.getNumberOfSheets (); k++ )
			{
				// Get the first sheet
				Sheet sheet = w.getSheet ( k );
				for ( int row = 0; row < sheet.getRows (); row++ )
				{
					columnDataList = new ArrayList<String>();
					for ( int col = 0; col < sheet.getColumns (); col++ )
					{
						
						Cell cell = sheet.getCell ( col, row );
						CellType type = cell.getType ();
						content = cell.getContents();
						System.out.println("cell.getContents () for row:"+row+" col::"+col+" :::"+cell.getContents ());
						columnDataList.add(content);						
					}
					rowDataList.add((ArrayList<String>) columnDataList);
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return rowDataList;
	}
	
	public void read ( File inputWorkbook ) throws IOException
	{
		boolean exists = false;
		List<GeoImportHHRegDTO> geoImportHHRegDTOList = new ArrayList<GeoImportHHRegDTO> ();
		GeoImportHHRegDTO geoImportHHRegDTO = null;
		List<SkDataDTO> skDataDTOList = null;
		SkDataDTO skDataDTO = null;
		
		
		String cityCorpId = "";
		String prevCityCorpId = "";		
		String ssId = "";
		String prevSSId = "";		
		String skId = "";
		String prevSKId = "";	
		
		Workbook w;
		try
		{
			w = Workbook.getWorkbook ( inputWorkbook );
			for ( int k = 0; k < w.getNumberOfSheets (); k++ )
			{
				// Get the first sheet
				Sheet sheet = w.getSheet ( k );
				geoImportHHRegDTO = new GeoImportHHRegDTO ();
				skDataDTOList = new ArrayList<SkDataDTO> ();
				// Loop over first 10 column and lines
				// System.out.println("columns:::" + sheet.getColumns());
				for ( int row = 0; row < sheet.getRows (); row++ )
				{
					if ( row > 2 )
					{
						for ( int col = 0; col < sheet.getColumns (); col++ )
						{
							
							Cell cell = sheet.getCell ( col, row );
							CellType type = cell.getType ();
							
							// get the city corporation
							
							// if city corportation is null go to sk column
							switch ( col )
							{							
								case ApplicationConstants.GEO_IMPORT_FILE_CITYCORPID_INDEX :							
									cityCorpId = cell.getContents ();		
									if(cityCorpId!=null && !cityCorpId.equals ( "null" ) && cityCorpId.length ()>0)
									{
										geoImportHHRegDTO = this.loadGeoInfo ( sheet,geoImportHHRegDTO );
									}	
									break;
								case ApplicationConstants.GEO_IMPORT_FILE_SKID_INDEX:
									skId = cell.getContents ();
									if ( skId != null && !skId.equals ( "null" ) && skId.length ()>0 && !skId.equals ( prevSKId ) )
									{		
										skDataDTO = this.populateSkDataDTOList ( sheet, row );
										skDataDTOList.add ( skDataDTO );
										prevSKId = skId;
									}
									break;
								case ApplicationConstants.GEO_IMPORT_FILE_SSID_INDEX:
									ssId = cell.getContents ();	
									if ( ssId != null && !ssId.equals ( prevSSId ) )
									{
										skDataDTOList = this.populateSkDataDTO(skDataDTOList,skDataDTO,sheet,row);
										prevSSId = ssId;
									}
									break;
								default :
									break;
							}
						}
								
					}
				}
				geoImportHHRegDTO.setSkDataDTOList ( skDataDTOList );
				geoImportHHRegDTOList.add ( geoImportHHRegDTO );
			}
			//geoImportHHRegDTOList = new ArrayList<GeoImportHHRegDTO> ();
			
			for(GeoImportHHRegDTO geoImportHHRegDTO2:geoImportHHRegDTOList)
			{
				System.out.println("data::"+geoImportHHRegDTO2.toString());
			}
			
			/*try
			{
				if(geoImportHHRegDTOList!=null && geoImportHHRegDTOList.size()>0)
				{
					geoService.importData ( geoImportHHRegDTOList );
				}
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}*/
		}
		catch (BiffException e)
		{
			e.printStackTrace ();
		}
	}
	public SkDataDTO populateSkDataDTOList(Sheet sheet,int row)
	{
		String skId = "";
		String skName = "";
		SkDataDTO skDataDTO = new SkDataDTO ();
		Cell cell = sheet.getCell ( ApplicationConstants.GEO_IMPORT_FILE_SKID_INDEX, row );
		skId = cell.getContents ();
		cell = sheet.getCell ( ApplicationConstants.GEO_IMPORT_FILE_SKNAME_INDEX, row );
		skName = cell.getContents ();
		System.out.println ("skId::"+skId+" skName::"+skName);
		skDataDTO.setSkId ( skId );
		skDataDTO.setSkName ( skName );
		
		return skDataDTO;
	}
	public List<SkDataDTO> populateSkDataDTO(List<SkDataDTO> skDataDTOList,SkDataDTO skDataDTO,Sheet sheet,int row)
	{
		String uniqueCode = "";
		String firstName = "";
		String lastName = "";
		List<SSDTO> ssDTOList =  skDataDTO.getSsDTOList ();
		if(ssDTOList == null)
		{
			ssDTOList = new ArrayList<SSDTO> ();
		}
		Cell cell = sheet.getCell ( ApplicationConstants.GEO_IMPORT_FILE_SSID_INDEX, row );
		uniqueCode = cell.getContents ();
		cell = sheet.getCell ( ApplicationConstants.GEO_IMPORT_FILE_SSNAME_INDEX, row );
		firstName =  cell.getContents ();
		SSDTO ssdto = new SSDTO ();
		ssdto.setSkId ( skDataDTO.getSkId () );
		ssdto.setSsId ( uniqueCode );		
		ssDTOList.add ( ssdto );		
		skDataDTO.setSsDTOList ( ssDTOList );
		System.out.println ("uniqueCode::"+uniqueCode+" firstName::"+firstName +" sk::"+skDataDTO.getSkId ());
		for(int i=0;skDataDTOList!=null && i<skDataDTOList.size ();i++)
		{
			SkDataDTO chSkDataDTO = skDataDTOList.get ( i );
			if(skDataDTO.getSkId () == chSkDataDTO.getSkId ())
			{
				skDataDTOList.remove ( chSkDataDTO );
			}
		}
		skDataDTOList.add ( skDataDTO );
		return skDataDTOList;
	}
	
	public GeoImportHHRegDTO loadGeoInfo(Sheet sheet,GeoImportHHRegDTO geoImportHHRegDTO)
	{
		for ( int row = 0; row < sheet.getRows (); row++ )
		{
			if ( row == 3 )
			{
				for ( int col = 0; col < sheet.getColumns (); col++ )
				{
					Cell cell = sheet.getCell ( col, row );
					CellType type = cell.getType ();
					switch ( col )
					{							
						case ApplicationConstants.GEO_IMPORT_FILE_CITYCORPID_INDEX :
							geoImportHHRegDTO.setCityCorpId ( cell.getContents () );
						case ApplicationConstants.GEO_IMPORT_FILE_CITYCORPNAME_INDEX :
							geoImportHHRegDTO.setCityCorpName ( cell.getContents () );
						case ApplicationConstants.GEO_IMPORT_FILE_REGIONID_INDEX :
							geoImportHHRegDTO.setRegionId ( cell.getContents () );
						case ApplicationConstants.GEO_IMPORT_FILE_REGIONNAME_INDEX :
							geoImportHHRegDTO.setRegionName ( cell.getContents () );
						case ApplicationConstants.GEO_IMPORT_FILE_BRANCHID_INDEX :
							geoImportHHRegDTO.setBranchId ( cell.getContents () );
						case ApplicationConstants.GEO_IMPORT_FILE_BRANCHNAME_INDEX :
							geoImportHHRegDTO.setBranchName ( cell.getContents () );
					}
					
				}
				
			}
		}
		
		return geoImportHHRegDTO;		
	}
}
