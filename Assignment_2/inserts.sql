-- Create developers and users
INSERT INTO `person` (`id`, `username`, `password`, `firstname`, `lastname`, `email`)
  VALUES
    (12, 'alice', 'alice', 'Alice', 'Wonder', 'alice@wonder.com'),
    (23, 'bob', 'bob', 'Bob', 'Marley', 'bob@marley.com'),
    (34, 'charlie', 'charlie', 'Charles', 'Garcia', 'chuch@garcia.com'),
    (45, 'dan', 'dan', 'Dan', 'Martin', 'dan@martin.com'),
    (56, 'ed', 'ed', 'Ed', 'Karaz', 'ed@kar.com')
  ;

INSERT INTO `developer` (`id`, `person`, `developer_key`)
  VALUES
    (12, 12, '4321rewq'),
    (23, 23, '5432trew'),
    (34, 34, '6543ytre');

INSERT INTO `user` (`id`, `person`)
  VALUES
    (45, 45),
    (56, 56);

-- Create web sites for developers
INSERT INTO `website` (`id`, `name`, `description`, `visits`, `created`, `updated`)
  VALUES
    (123, 'Facebook', 'an online social media and social networking service', 1234234, CURDATE(), CURDATE()),
    (234, 'Twitter', 'an online news and social networking service', 4321543, CURDATE(), CURDATE()),
    (345, 'Wikipedia', 'a free online encyclopedia', 3456654, CURDATE(), CURDATE()),
    (456, 'CNN', 'an American basic cable and satellite television news channel', 6543345, CURDATE(), CURDATE()),
    (567, 'CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics', 5433455, CURDATE(), CURDATE()),
    (678, 'Gizmodo', 'a design, technology, science and science fiction website that also writes articles on politics', 4322345, CURDATE(), CURDATE());

INSERT INTO `website_role` (`role`, `developer`, `website`)
  VALUES
    ('owner', 12, 123),
    ('editor', 23, 123),
    ('admin', 34, 123),
    ('owner', 23, 234),
    ('editor', 34, 234),
    ('admin', 12, 234),
    ('owner', 34, 345),
    ('editor', 12, 345),
    ('admin', 23, 345),
    ('owner', 12, 456),
    ('editor', 23, 456),
    ('admin', 34, 456),
    ('owner', 23, 567),
    ('editor', 34, 567),
    ('admin', 12, 567),
    ('owner', 34, 678),
    ('editor', 12, 678),
    ('admin', 23, 678);

-- Create pages for web sites
INSERT INTO `page` (`id`, `title`, `description`, `website`, `views`, `created`, `updated`)
  VALUES
    (123, 'Home', 'Landing page', 567, 123434, CURDATE(), CURDATE()),
    (234, 'About', 'Website description', 678, 234545, CURDATE(), CURDATE()),
    (345, 'Contact', 'Addresses, phones, and contact info', 345, 345656, CURDATE(), CURDATE()),
    (456, 'Preferences', 'Where users can configure their preferences', 456, 456776, CURDATE(), CURDATE()),
    (567, 'Profile', 'Users can configure their personal information', 567, 567878, CURDATE(), CURDATE());

INSERT INTO `page_role` (`role`, `developer`, `page`)
  VALUES
    ('editor', 12, 123),
    ('reviewer', 23, 123),
    ('writer', 34, 123),
    ('editor', 23, 234),
    ('reviewer', 34, 234),
    ('writer', 12, 234),
    ('editor', 34, 345),
    ('reviewer', 12, 345),
    ('writer', 23, 345),
    ('editor', 12, 456),
    ('reviewer', 23, 456),
    ('writer', 34, 456),
    ('editor', 23, 567),
    ('reviewer', 34, 567),
    ('writer', 12, 567);

-- Create widgets for pages
INSERT INTO `widget` (`id`, `name`, `dtype`, `text`, `order`, `width`, `height`, `image_src`, `youtube_url`, `page`)
  VALUES
    (123, 'head123', 'heading', 'Welcome', 0, NULL, NULL, NULL, NULL, 123),
    (234, 'post234', 'html', '<p>Lorem</p>', 0, NULL, NULL, NULL, NULL, 234),
    (345, 'head345', 'heading', 'Hi', 1, NULL, NULL, NULL, NULL, 345),
    (456, 'intro456', 'html', '<h1>Hi</h1>', 2, NULL, NULL, NULL, NULL, 345),
    (567, 'image345', 'image', NULL, 3, 50, 100, '/img/567.png', NULL, 345),
    (678, 'video456', 'youtube', NULL, 0, 400, 300, NULL, 'https://youtu.be/h67VX51QXiQ', 456);

-- Create phones and addresses
INSERT INTO `phone` (`phone`, `primary`, `person`)
  VALUES
    ('123-234-3456', 1, 12),
    ('234-345-4566', 0, 12),
    ('345-456-5677', 1, 23),
    ('321-432-5435', 1, 34),
    ('432-432-5433', 0, 34),
    ('543-543-6544', 0, 34);

INSERT INTO `address` (`street1`, `city`, `zip`, `primary`, `person`)
  VALUES
    ('123 Adam St.', 'Alton', '01234', 1, 12),
    ('234 Birch St.', 'Boston', '02345', 0, 12),
    ('345 Charles St.', 'Chelms', '03455', 1, 23),
    ('456 Down St.', 'Dalton', '04566', 0, 23),
    ('543 East St.', 'Everett', '01112', 0, 23),
    ('654 Frank St.', 'Foulton', '04322', 1, 34);