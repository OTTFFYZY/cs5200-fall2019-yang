-- Update developer - Update Charlie's primary phone number to 333-444-5555
UPDATE `phone` ph
  SET ph.`phone` = '333-444-5555'
  WHERE ph.`id` = (
    SELECT tmp.id 
    FROM (
      SELECT ph2.id
        FROM `phone` ph2
        INNER JOIN `person` p ON p.id = ph2.person
        WHERE p.`id` = ph2.`person` AND p.`username` = 'charlie' AND ph2.`primary` = 1
    ) tmp
  );

-- Update widget - Update the relative order of widget head345 on the page so that it's new order is 3. 
-- Note that the other widget's order needs to update as well
UPDATE `widget` wg
  SET wg.`order` = (
    CASE WHEN wg.`order` = 1 THEN 3
    ELSE wg.`order` - 1 END
  )
  WHERE wg.`page` = (
    SELECT tmp.id
      FROM 
      (
        SELECT DISTINCT pg.id
          FROM page pg
            INNER JOIN `widget` wg2 ON wg2.page = pg.id
          WHERE wg2.name = 'head345'
    ) tmp
  );


-- Update page - Append 'CNET - ' to the beginning of all CNET's page titles
UPDATE `page` pg
  SET pg.title = CONCAT ('CNET - ', title)
  WHERE pg.website = (
    SELECT tmp.id
      FROM (
        SELECT DISTINCT w.id
          FROM `page` pg2
            INNER JOIN `website` w ON w.id = pg2.website
          WHERE w.name = 'CNET'
      ) tmp
  );


-- Update roles - Swap Charlie's and Bob's role in CNET's Home page
SELECT @charlie_role := pr.`role`
  FROM `page` pg
    INNER JOIN `page_role` pr ON pr.`page` = pg.id
    INNER JOIN `website` w ON w.`id` = pg.`website`
    INNER JOIN `person` p ON p.`id` = pr.`developer`
  WHERE pg.title = 'CNET - Home' AND p.username = 'charlie' AND w.name = 'CNET';
  
SELECT @bob_role := pr.`role`
  FROM `page` pg
    INNER JOIN `page_role` pr ON pr.`page` = pg.id
    INNER JOIN `website` w ON w.`id` = pg.`website`
    INNER JOIN `person` p ON p.`id` = pr.`developer`
  WHERE pg.title = 'CNET - Home' AND p.username = 'bob' AND w.name = 'CNET';

-- SELECT @charlie_role, @bob_role;

UPDATE `page_role` pr
  SET pr.`role` = (
    CASE WHEN pr.`role` = @charlie_role THEN @bob_role
         WHEN pr.`role` = @bob_role THEN @charlie_role
         ELSE NULL END
  )
  WHERE pr.`page` = (
  SELECT tmp.id 
      FROM
      (
        SELECT DISTINCT pg.id
          FROM `page` pg
            INNER JOIN `website` w ON w.`id` = pg.`website`
      WHERE pg.title = 'CNET - Home' AND w.name = 'CNET'
      ) tmp
  )
    AND pr.`developer` IN (
      SELECT p.`id`
        FROM `person` p
        WHERE p.`username` = 'charlie' or p.`username` = 'bob'
    );