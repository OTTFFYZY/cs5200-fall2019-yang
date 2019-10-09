DELIMITER && 
-- Create website privilege triggers
CREATE TRIGGER `website_privilege_insert_trigger`    
  AFTER INSERT ON `website_role`   
  FOR EACH ROW   
  BEGIN   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'create', NEW.`website`, NEW.`developer`       
      WHERE NEW.role IN ('owner', 'admin', 'writer')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  ( 
    SELECT 'read', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor', 'reviewer')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'update', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'delete', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin')   
  );   
  END &&

CREATE TRIGGER `website_privilege_delete_trigger` 
  AFTER DELETE ON `website_role`
  FOR EACH ROW
  BEGIN
    DELETE FROM `website_privilege`
      WHERE `website_privilege`.`website` = OLD.`website` 
        AND `website_privilege`.`developer` = OLD.`developer`;
  END &&

CREATE TRIGGER `website_privilege_update_trigger` 
  AFTER UPDATE ON `website_role`
  FOR EACH ROW
  BEGIN
    DELETE FROM `website_privilege`
      WHERE `website_privilege`.`website` = OLD.`website` 
        AND `website_privilege`.`developer` = OLD.`developer`;
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'create', NEW.`website`, NEW.`developer`       
      WHERE NEW.role IN ('owner', 'admin', 'writer')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  ( 
    SELECT 'read', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor', 'reviewer')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'update', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor')   
  );   
  INSERT INTO `website_privilege` (`privilege`, `website`, `developer`)   
  (     
    SELECT 'delete', NEW.website, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin')   
  );
  END &&

-- Create page privilege triggers
CREATE TRIGGER `page_privilege_insert_trigger`    
  AFTER INSERT ON `page_role`   
  FOR EACH ROW   
  BEGIN   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'create', NEW.`page`, NEW.`developer`       
      WHERE NEW.role IN ('owner', 'admin', 'writer')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  ( 
    SELECT 'read', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor', 'reviewer')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'update', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'delete', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin')   
  );   
  END &&

CREATE TRIGGER `page_privilege_delete_trigger` 
  AFTER DELETE ON `page_role`
  FOR EACH ROW
  BEGIN
    DELETE FROM `page_privilege`
      WHERE `page_privilege`.`page` = OLD.`page` 
        AND `page_privilege`.`developer` = OLD.`developer`;
  END &&

CREATE TRIGGER `page_privilege_update_trigger` 
  AFTER UPDATE ON `page_role`
  FOR EACH ROW
  BEGIN
    DELETE FROM `page_privilege`
      WHERE `page_privilege`.`page` = OLD.`page` 
        AND `page_privilege`.`developer` = OLD.`developer`;
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'create', NEW.`page`, NEW.`developer`       
      WHERE NEW.role IN ('owner', 'admin', 'writer')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  ( 
    SELECT 'read', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor', 'reviewer')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'update', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin', 'writer', 'editor')   
  );   
  INSERT INTO `page_privilege` (`privilege`, `page`, `developer`)   
  (     
    SELECT 'delete', NEW.page, NEW.developer       
    WHERE NEW.role IN ('owner', 'admin')   
  );
  END &&

DELIMITER ;