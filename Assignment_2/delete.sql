-- Delete developer - Delete Alice's primary address
DELETE FROM `address`
  WHERE `person` = (
    SELECT tmp.id
      FROM (
        SELECT p.id
          FROM `person` p 
          WHERE p.username = 'alice'
      ) tmp
  ) AND `primary` = 1;

-- Delete widget - Remove the last widget in the Contact page. 
-- The last widget is the one with the highest value in the order field
DELETE wg FROM `widget` wg
  WHERE wg.id = (
    SELECT tmp.id
      FROM (
        SELECT wg2.id
          FROM `widget` wg2
            INNER JOIN `page` pg ON wg2.page = pg.id
          WHERE pg.title = 'Contact'
          ORDER BY wg2.order DESC
          LIMIT 1
      ) tmp
  );


-- Delete page - Remove the last updated page in Wikipedia
START TRANSACTION;

DELETE pr FROM `page_role` pr
  WHERE pr.page = (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'Wikipedia'
          ORDER BY pg2.updated DESC
          LIMIT 1
      ) tmp
  );

DELETE wg FROM `widget` wg
  WHERE wg.page = (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'Wikipedia'
          ORDER BY pg2.updated DESC
          LIMIT 1
      ) tmp
  );

DELETE pg FROM `page` pg
  WHERE pg.id = (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'Wikipedia'
          ORDER BY pg2.updated DESC
          LIMIT 1
      ) tmp
  );

COMMIT;

-- Delete website - Remove the CNET web site, 
-- as well as all related roles and privileges relating developers to the Website and Pages
START TRANSACTION;
DELETE wg FROM `widget` wg
  WHERE wg.page IN (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'CNET'
      ) tmp
  );

DELETE pr FROM `page_role` pr
  WHERE pr.page IN (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'CNET'
      ) tmp
  );

DELETE pg FROM `page` pg
  WHERE pg.id IN (
    SELECT tmp.id
      FROM (
        SELECT pg2.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'CNET'
      ) tmp
  );

DELETE wr FROM `website_role` wr
  WHERE wr.website = (
    SELECT tmp.id
      FROM (
        SELECT w.id
          FROM `website` w
          WHERE w.name = 'CNET'
      ) tmp
  );

DELETE w FROM `website` w
  WHERE w.name = 'CNET';

COMMIT;