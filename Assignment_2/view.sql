CREATE VIEW deleveloper_roles_and_privileges AS
    SELECT p.firstname AS 'firstname', 
           p.lastname AS 'lastname', 
           p.username AS 'username', 
           p.email AS 'email',
           'website' AS 'dtype',
           w.name AS 'website_or_title',
           w.visits AS 'visits_or_views', 
           w.updated AS 'last_updated', 
           wr.role AS 'role',
           SUM(CASE WHEN wp.privilege = 'create' THEN 1 ELSE 0 END) AS 'create',
           SUM(CASE WHEN wp.privilege = 'read' THEN 1 ELSE 0 END) AS 'read',
           SUM(CASE WHEN wp.privilege = 'update' THEN 1 ELSE 0 END) AS 'update',
           SUM(CASE WHEN wp.privilege = 'delete' THEN 1 ELSE 0 END) AS 'delete'
      FROM `developer` d
        INNER JOIN `person` p ON p.id = d.person
        INNER JOIN `website_role` wr ON d.id = wr.developer
        INNER JOIN `website` w ON w.id = wr.website
        INNER JOIN `website_privilege` wp ON d.id = wp.developer AND w.id = wp.website
      GROUP BY d.id, w.id
    UNION ALL
    SELECT p.firstname AS 'firstname', 
           p.lastname AS 'lastname', 
           p.username AS 'username', 
           p.email AS 'email',
           'page' AS 'dtype',
           pg.title AS 'website_or_title',
           pg.views AS 'visits_or_views', 
           pg.updated AS 'last_updated', 
           pr.role AS 'role',
           SUM(CASE WHEN pp.privilege = 'create' THEN 1 ELSE 0 END) AS 'create',
           SUM(CASE WHEN pp.privilege = 'read' THEN 1 ELSE 0 END) AS 'read',
           SUM(CASE WHEN pp.privilege = 'update' THEN 1 ELSE 0 END) AS 'update',
           SUM(CASE WHEN pp.privilege = 'delete' THEN 1 ELSE 0 END) AS 'delete'
      FROM `developer` d
        INNER JOIN `person` p ON p.id = d.person
        INNER JOIN `page_role` pr ON d.id = pr.developer
        INNER JOIN `page` pg ON pg.id = pr.page
        INNER JOIN `page_privilege` pp ON d.id = pp.developer AND pg.id = pp.page
      GROUP BY d.id, pg.id;