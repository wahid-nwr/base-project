SELECT  CONCAT(REPEAT('    ', level - 1), CAST(hi.id AS CHAR)) AS treeitem, parent, level, lmt
FROM    (
        SELECT  hierarchy_connect_by_parent_eq_prior_id(id) AS id, @level AS level, @parent_limit as lmt
        FROM    (
                SELECT  @start_with := 0,
                        @parent_limit := 4,
                        @id := @start_with,
                        @level := 0
                ) vars, t_hierarchy
        WHERE   @id IS NOT NULL
        ) ho
JOIN    t_hierarchy hi
ON      hi.id = ho.id
