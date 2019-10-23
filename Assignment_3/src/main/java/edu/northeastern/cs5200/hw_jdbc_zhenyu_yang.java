package edu.northeastern.cs5200;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;
public class hw_jdbc_zhenyu_yang {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeveloperDao developerDao = new DeveloperImpl();
		UserDao userDao = new UserImpl();
		WebsiteDao websiteDao = new WebsiteImpl();
		RoleDao roleDao = new RoleImpl();
		PageDao pageDao =new PageImpl();
		WidgetDao widgetDao = new WidgetImpl();
		
		// create person
		Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null);
		Developer bob = new	Developer("5432trew", 23, "Bob", "Marley", "bob", "bob",  "bob@marley.com", null);
        Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie",  "chuch@garcia.com", null);
        
        
        developerDao.createDeveloper(alice);
        developerDao.createDeveloper(bob);
        developerDao.createDeveloper(charlie);
        
        User dan = new User(45, "dan", "dan", "Dan", "Martin", "dan@martin.com", null);
        User ed = new User(56, "ed", "ed", "Ed", "Karaz", "ed@kar.com", null);
        
        userDao.createUser(dan);
        userDao.createUser(ed);
        
        
        // create website
        Date gradeDate = new Date(Calendar.getInstance().getTime().getTime());
        Website facebook = new Website(123, "Facebook", "an online social media and social networking service", gradeDate, gradeDate, 1234234);
        Website twitter = new Website(234, "Twitter", "an online news and social networking service", gradeDate, gradeDate, 4321543);
        Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", gradeDate, gradeDate, 3456654);
        Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", gradeDate, gradeDate, 6543345);
        Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", gradeDate, gradeDate, 5433455);
        Website gizmodo = new Website(678,"Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", gradeDate, gradeDate, 4322345);
        
        
        websiteDao.createWebsiteForDeveloper(alice.getId(), facebook);
        websiteDao.createWebsiteForDeveloper(bob.getId(), twitter);
        websiteDao.createWebsiteForDeveloper(charlie.getId(), wikipedia);
        websiteDao.createWebsiteForDeveloper(alice.getId(), cnn);       
        websiteDao.createWebsiteForDeveloper(bob.getId(), cnet);
        websiteDao.createWebsiteForDeveloper(charlie.getId(), gizmodo);
        
        
        roleDao.assignWebsiteRole(alice.getId(), facebook.getId(), "owner");
        roleDao.assignWebsiteRole(bob.getId(), facebook.getId(), "editor");
        roleDao.assignWebsiteRole(charlie.getId(), facebook.getId(), "admin");
        
        roleDao.assignWebsiteRole(bob.getId(), twitter.getId(), "owner");
        roleDao.assignWebsiteRole(charlie.getId(), twitter.getId(), "editor");
        roleDao.assignWebsiteRole(alice.getId(), twitter.getId(), "admin");
        
        roleDao.assignWebsiteRole(charlie.getId(), wikipedia.getId(), "owner");
        roleDao.assignWebsiteRole(alice.getId(), wikipedia.getId(), "editor");
        roleDao.assignWebsiteRole(bob.getId(), wikipedia.getId(), "admin");
        
        roleDao.assignWebsiteRole(alice.getId(), cnn.getId(), "owner");
        roleDao.assignWebsiteRole(bob.getId(), cnn.getId(), "editor");
        roleDao.assignWebsiteRole(charlie.getId(), cnn.getId(), "admin");
        
        roleDao.assignWebsiteRole(bob.getId(), cnet.getId(), "owner");
        roleDao.assignWebsiteRole(charlie.getId(), cnet.getId(), "editor");
        roleDao.assignWebsiteRole(alice.getId(), cnet.getId(), "admin");
        
        roleDao.assignWebsiteRole(charlie.getId(), gizmodo.getId(), "owner");
        roleDao.assignWebsiteRole(alice.getId(), gizmodo.getId(), "editor");
        roleDao.assignWebsiteRole(bob.getId(), gizmodo.getId(), "admin");
        
        
        // create page;
        
        Date startDate = Date.valueOf("2019-09-03");
        Date endDate= Date.valueOf("2019-10-20");
        
        Page home = new Page(123, "Home", "Landing page", startDate, endDate, 123434);
        Page about = new Page(234, "About", "Website description", startDate, endDate, 234545);
        Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", startDate, endDate, 345656);
        Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", startDate, endDate, 456776);
        Page profile = new Page(567, "Profile", "Users can configure their personal information", startDate, endDate, 567878);

     
        pageDao.createPageForWebsite(cnet.getId(), home);
        pageDao.createPageForWebsite(gizmodo.getId(), about);
        pageDao.createPageForWebsite(wikipedia.getId(), contact);
        pageDao.createPageForWebsite(cnn.getId(), preferences);
        pageDao.createPageForWebsite(cnet.getId(), profile);
        
        roleDao.assignPageRole(alice.getId(), home.getId(), "editor");
        roleDao.assignPageRole(bob.getId(), home.getId(), "reviewer");
        roleDao.assignPageRole(charlie.getId(), home.getId(), "writer");
        
        roleDao.assignPageRole(bob.getId(), about.getId(), "editor");
        roleDao.assignPageRole(charlie.getId(), about.getId(), "reviewer");
        roleDao.assignPageRole(alice.getId(), about.getId(), "writer");
        
        roleDao.assignPageRole(charlie.getId(), contact.getId(), "editor");
        roleDao.assignPageRole(alice.getId(), contact.getId(), "reviewer");
        roleDao.assignPageRole(bob.getId(), contact.getId(), "writer");
        
        roleDao.assignPageRole(alice.getId(), preferences.getId(), "editor");
        roleDao.assignPageRole(bob.getId(), preferences.getId(), "reviewer");
        roleDao.assignPageRole(charlie.getId(), preferences.getId(), "writer");
        
        roleDao.assignPageRole(bob.getId(), profile.getId(), "editor");
        roleDao.assignPageRole(charlie.getId(), profile.getId(), "reviewer");
        roleDao.assignPageRole(alice.getId(), profile.getId(), "writer");

        // create widget
        HeadingWidget head123 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0, 0);
        head123.setPage(123);
        HtmlWidget post234 =  new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, null);
        post234.setPage(234);
        HeadingWidget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1, 0);
        head345.setPage(345);
        HtmlWidget intro456 = new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, null);
        intro456.setPage(345);
        ImageWidget image345 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3, "/img/567.png");
        image345.setPage(345);
        YouTubeWidget video456 = new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0, "https://youtu.be/h67VX51QXiQ", 0, 0);
        video456.setPage(456);
        
    
        widgetDao.createWidgetForPage(123, head123);
        widgetDao.createWidgetForPage(234, post234);
        widgetDao.createWidgetForPage(345, head345);
        widgetDao.createWidgetForPage(345, intro456);
        widgetDao.createWidgetForPage(345, image345);
        widgetDao.createWidgetForPage(456, video456);
        
        System.out.println("Create Success!");
        
		
		// update developer
		ArrayList<Phone> charliePhones = new ArrayList<>();
		charliePhones.add(new Phone(0, "333-444-5555", 1, charlie.getId()));
		charlie.setPhones(charliePhones);
		developerDao.updateDeveloper(charlie.getId(), charlie);
		
		// updatge widget
		head345.setOrder(3);
		intro456.setOrder(1);
		image345.setOrder(2);
		widgetDao.updateWidget(head345.getId(),head345);
		widgetDao.updateWidget(intro456.getId(),intro456);
		widgetDao.updateWidget(image345.getId(),image345);
		
		// update page
		Collection<Page> pages = pageDao.findPagesForWebsite(cnet.getId());
		for(Page page : pages) {
			page.setTitle("CNET - " + page.getTitle());
			pageDao.updatePage(page.getId(), page);
		}
		
		// update roles
		roleDao.deleteWebsiteRole(bob.getId(), cnet.getId(), "owner");
		roleDao.deleteWebsiteRole(charlie.getId(), cnet.getId(), "editor");
		roleDao.assignPageRole(bob.getId(), cnet.getId(), "editor");
		roleDao.assignPageRole(charlie.getId(), cnet.getId(), "owner");
		
		System.out.println("Update Success");
		
		// delete developer
		ArrayList<Address> aliceAddresses = alice.getAddresses();
		if(aliceAddresses != null) 
			aliceAddresses.removeIf(x -> x.getPrimary() == 1);
		developerDao.updateDeveloper(alice.getId(), alice);
		
		// delete widget
		Collection<Widget> widgets = widgetDao.findAllWidgets();
		int maxOrder = -1, wid = -1;
		for(Widget w : widgets) {
			if(maxOrder < w.getOrder()) {
				maxOrder = w.getOrder();
				wid = w.getId();
			}
		}
		if(wid != -1)
			widgetDao.deleteWidget(wid);
		
		// delete page
		pages = pageDao.findPagesForWebsite(wikipedia.getId());
		Date latest = null;
		int pid = -1;
		for(Page p : pages) {
			if(pid == -1 || latest.compareTo(p.getUpdated()) < 0) {
				latest = p.getUpdated();
				pid = p.getId();
			}
		}
		//System.out.println("11111111111111111111111 " + pid);
		for(Widget w : widgetDao.findWidgetsForPage(pid)) {
			widgetDao.deleteWidget(w.getId());
		}
		roleDao.deletePageRoleByPage(pid);
		pageDao.deletePage(pid);
		
		// delete website
		pages = pageDao.findPagesForWebsite(cnet.getId());
		for(Page p : pages) {
			for(Widget w : widgetDao.findWidgetsForPage(p.getId())) {
				widgetDao.deleteWidget(w.getId());
			}
			roleDao.deletePageRoleByPage(p.getId());
			pageDao.deletePage(p.getId());
		}
		roleDao.deleteWebsiteRoleByWebsite(cnet.getId());
		websiteDao.deleteWebsite(cnet.getId());
		
		System.out.println("Delete Success");
		System.out.println("All Done!");
	}

}
