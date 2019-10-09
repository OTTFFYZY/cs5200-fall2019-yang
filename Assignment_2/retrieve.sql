-- Retrieve developers
-- a.  Retrieve all developers
SELECT p.id, p.firstname, p.lastname, p.username, p.email 
  FROM `developer` d
    INNER JOIN `person` p ON d.person = p.id;

-- b.  Retrieve a developer with id equal to 34 
SELECT p.id, p.firstname, p.lastname, p.username, p.email 
  FROM `developer` d
    INNER JOIN `person` p ON d.person = p.id
  WHERE d.id = 34;

-- c.  Retrieve all developers who have a role in Twitter other than owner 
SELECT p.id, p.firstname, p.lastname, p.username, p.email 
  FROM `developer` d
    INNER JOIN `person` p ON d.person = p.id
    INNER JOIN `website_role` wr ON wr.developer = d.id
    INNER JOIN `website` w ON w.id = wr.website
  WHERE w.name = 'Twitter' AND NOT wr.role = 'owner';

-- d.  Retrieve all developers who are page reviewers of pages with less than 300000 visits 
SELECT p.id, p.firstname, p.lastname, p.username, p.email 
  FROM `developer` d
    INNER JOIN `person` p ON d.person = p.id
    INNER JOIN `page_role` pr ON pr.developer = d.id
    INNER JOIN `page` pg ON pg.id = pr.page
  WHERE pr.role = 'reviewer' AND pg.views < 300000;

-- e.  Retrieve the writer developer who added a heading widget to CNET's home page
SELECT p.id, p.firstname, p.lastname, p.username, p.email
  FROM `developer` d
    INNER JOIN `person` p ON d.person = p.id
    INNER JOIN `page_role` pr ON pr.developer = d.id
    INNER JOIN `page` pg ON pg.id = pr.page
    INNER JOIN `website` w ON w.id = pg.website
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE pr.role = 'writer' AND wg.dtype = 'heading' AND w.name = 'CNET' AND pg.title = 'Home';


-- Retrieve websites
-- a.  Retrieve the website with the least number of visits
SELECT * 
  FROM website
  ORDER BY visits
  LIMIT 1;

-- b.  Retrieve the name of a website whose id is 678 
SELECT * 
  FROM website
  WHERE id = 678;

-- c.  Retrieve all websites with videos reviewed by bob
SELECT w.id, w.name, w.description 
  FROM `website` w
    INNER JOIN `page` pg ON pg.website = w.id
    INNER JOIN `page_role` pr ON pr.page = pg.id
    INNER JOIN `person` p ON p.id = pr.developer
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE pr.role = 'reviewer' AND p.username = 'bob'
  GROUP BY w.id
  HAVING SUM(CASE WHEN wg.dtype = 'youtube' THEN 1 ELSE 0 END) >= 1;

-- d.  Retrieve all websites where alice is an owner 
SELECT DISTINCT w.id, w.name, w.description 
  FROM `website` w
    INNER JOIN `website_role` wr ON wr.website = w.id
    INNER JOIN `person` p ON p.id = wr.developer
  WHERE p.username = 'alice' AND wr.role = 'owner';

-- e.  Retrieve all websites where charlie is an admin and get more than 6000000 visits
SELECT DISTINCT w.id, w.name, w.description 
  FROM `website` w
    INNER JOIN `website_role` wr ON wr.website = w.id
    INNER JOIN `person` p ON p.id = wr.developer
  WHERE p.username = 'charlie' AND wr.role = 'admin' AND w.visits > 6000000;


-- Retrieve pages
-- a.  Retrieve the page with the most number of views
SELECT *
  FROM page
  ORDER BY views DESC
  LIMIT 1;

-- b.  Retrieve the title of a page whose id is 234
SELECT title
  FROM page
  WHERE id = 234;

-- c.  Retrieve all pages where alice is an editor 
SELECT DISTINCT pg.id, pg.title, pg.description
  FROM `page` pg
    INNER JOIN `page_role` pr ON pg.id = pr.page
    INNER JOIN `person` p ON p.id = pr.developer
  WHERE p.username = 'alice' AND pr.role = 'editor';

-- d.  Retrieve the total number of pageviews in CNET
SELECT SUM(pg.views) total_number
  FROM `page` pg
    INNER JOIN `website` w ON w.id = pg.website
  WHERE w.name = 'CNET';

-- e.  Retrieve the average number of page views in the Website Wikipedia
SELECT AVG(pg.views) average_views
  FROM `page` pg
    INNER JOIN `website` w ON w.id = pg.website
  WHERE w.name = 'Wikipedia';


-- Retrieve widgets
-- a.  Retrieve all widgets in CNET's Home page
SELECT DISTINCT wg.id, wg.name
  FROM `website` w
    INNER JOIN `page` pg ON pg.website = w.id
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE w.name = 'CNET' AND pg.title = 'Home';

-- b.  Retrieve all youtube widgets in CNN
SELECT DISTINCT wg.id, wg.name
  FROM `website` w
    INNER JOIN `page` pg ON pg.website = w.id
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE wg.dtype = 'youtube' AND w.name = 'CNN';

-- c.  Retrieve all image widgets on pages reviewed by Alice
SELECT DISTINCT wg.id, wg.name
  FROM `page` pg
    INNER JOIN `page_role` pr ON pr.page = pg.id
    INNER JOIN `person` p ON p.id = pr.developer
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE wg.dtype = 'image' AND p.username = 'alice' AND pr.role = 'reviewer';

-- d.  Retrieve how many widgets are in Wikipedia
SELECT COUNT(*) number_of_widgets
  FROM `website` w
    INNER JOIN `page` pg ON pg.website = w.id
    INNER JOIN `widget` wg ON wg.page = pg.id
  WHERE w.name = 'Wikipedia';


-- To verify the page and website triggers written earlier function properly:
-- a.  Retrieve the names of all the websites where Bob has DELETE privileges.
SELECT DISTINCT w.name
  FROM `website` w
    INNER JOIN `website_privilege` wp ON wp.website = w.id
    INNER JOIN `person` p ON p.id = wp.developer
  WHERE p.username = 'bob' AND wp.privilege = 'delete';

-- b.  Retrieve the names of all the pages where Charlie has CREATE privileges.
SELECT DISTINCT pg.title
  FROM `page` pg
    INNER JOIN `page_privilege` pp ON pp.page = pg.id
    INNER JOIN `person` p ON p.id = pp.developer
  WHERE p.username = 'charlie' AND pp.privilege = 'create';
  