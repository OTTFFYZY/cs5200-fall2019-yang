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
		DeveloperImpl developerImpl = new DeveloperImpl();
		UserImpl userImpl = new UserImpl();
		WebsiteImpl websiteImpl = new WebsiteImpl();
		RoleImpl roleImpl = new RoleImpl();
		PageImpl pageImpl =new PageImpl();
		WidgetImpl widgetImpl = new WidgetImpl();
		
		// create person
		Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null);
		Developer bob = new	Developer("5432trew", 23, "Bob", "Marley", "bob", "bob",  "bob@marley.com", null);
        Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie",  "chuch@garcia.com", null);
        
        
        developerImpl.createDeveloper(alice);
        developerImpl.createDeveloper(bob);
        developerImpl.createDeveloper(charlie);
        
        User dan = new User(45, "dan", "dan", "Dan", "Martin", "dan@martin.com", null);
        User ed = new User(56, "ed", "ed", "Ed", "Karaz", "ed@kar.com", null);
        
        userImpl.createUser(dan);
        userImpl.createUser(ed);
        
        
        // create website
        Date gradeDate = new Date(Calendar.getInstance().getTime().getTime());
        Website facebook = new Website(123, "Facebook", "an online social media and social networking service", gradeDate, gradeDate, 1234234);
        Website twitter = new Website(234, "Twitter", "an online news and social networking service", gradeDate, gradeDate, 4321543);
        Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia", gradeDate, gradeDate, 3456654);
        Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", gradeDate, gradeDate, 6543345);
        Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", gradeDate, gradeDate, 5433455);
        Website gizmodo = new Website(678,"Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", gradeDate, gradeDate, 4322345);
        
        
        websiteImpl.createWebsiteForDeveloper(alice.getId(), facebook);
        websiteImpl.createWebsiteForDeveloper(bob.getId(), twitter);
        websiteImpl.createWebsiteForDeveloper(charlie.getId(), wikipedia);
        websiteImpl.createWebsiteForDeveloper(alice.getId(), cnn);       
        websiteImpl.createWebsiteForDeveloper(bob.getId(), cnet);
        websiteImpl.createWebsiteForDeveloper(charlie.getId(), gizmodo);
        
        
        roleImpl.assignWebsiteRole(alice.getId(), facebook.getId(), "owner");
        roleImpl.assignWebsiteRole(bob.getId(), facebook.getId(), "editor");
        roleImpl.assignWebsiteRole(charlie.getId(), facebook.getId(), "admin");
        
        roleImpl.assignWebsiteRole(bob.getId(), twitter.getId(), "owner");
        roleImpl.assignWebsiteRole(charlie.getId(), twitter.getId(), "editor");
        roleImpl.assignWebsiteRole(alice.getId(), twitter.getId(), "admin");
        
        roleImpl.assignWebsiteRole(charlie.getId(), wikipedia.getId(), "owner");
        roleImpl.assignWebsiteRole(alice.getId(), wikipedia.getId(), "editor");
        roleImpl.assignWebsiteRole(bob.getId(), wikipedia.getId(), "admin");
        
        roleImpl.assignWebsiteRole(alice.getId(), cnn.getId(), "owner");
        roleImpl.assignWebsiteRole(bob.getId(), cnn.getId(), "editor");
        roleImpl.assignWebsiteRole(charlie.getId(), cnn.getId(), "admin");
        
        roleImpl.assignWebsiteRole(bob.getId(), cnet.getId(), "owner");
        roleImpl.assignWebsiteRole(charlie.getId(), cnet.getId(), "editor");
        roleImpl.assignWebsiteRole(alice.getId(), cnet.getId(), "admin");
        
        roleImpl.assignWebsiteRole(charlie.getId(), gizmodo.getId(), "owner");
        roleImpl.assignWebsiteRole(alice.getId(), gizmodo.getId(), "editor");
        roleImpl.assignWebsiteRole(bob.getId(), gizmodo.getId(), "admin");
        
        
        // create page;
        
        Date startDate = Date.valueOf("2019-09-03");
        Date endDate= Date.valueOf("2019-10-20");
        
        Page home = new Page(123, "Home", "Landing page", startDate, endDate, 123434);
        Page about = new Page(234, "About", "Website description", startDate, endDate, 234545);
        Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", startDate, endDate, 345656);
        Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", startDate, endDate, 456776);
        Page profile = new Page(567, "Profile", "Users can configure their personal information", startDate, endDate, 567878);

     
        pageImpl.createPageForWebsite(cnet.getId(), home);
        pageImpl.createPageForWebsite(gizmodo.getId(), about);
        pageImpl.createPageForWebsite(wikipedia.getId(), contact);
        pageImpl.createPageForWebsite(cnn.getId(), preferences);
        pageImpl.createPageForWebsite(cnet.getId(), profile);
        
        roleImpl.assignPageRole(alice.getId(), home.getId(), "editor");
        roleImpl.assignPageRole(bob.getId(), home.getId(), "reviewer");
        roleImpl.assignPageRole(charlie.getId(), home.getId(), "writer");
        
        roleImpl.assignPageRole(bob.getId(), about.getId(), "editor");
        roleImpl.assignPageRole(charlie.getId(), about.getId(), "reviewer");
        roleImpl.assignPageRole(alice.getId(), about.getId(), "writer");
        
        roleImpl.assignPageRole(charlie.getId(), contact.getId(), "editor");
        roleImpl.assignPageRole(alice.getId(), contact.getId(), "reviewer");
        roleImpl.assignPageRole(bob.getId(), contact.getId(), "writer");
        
        roleImpl.assignPageRole(alice.getId(), preferences.getId(), "editor");
        roleImpl.assignPageRole(bob.getId(), preferences.getId(), "reviewer");
        roleImpl.assignPageRole(charlie.getId(), preferences.getId(), "writer");
        
        roleImpl.assignPageRole(bob.getId(), profile.getId(), "editor");
        roleImpl.assignPageRole(charlie.getId(), profile.getId(), "reviewer");
        roleImpl.assignPageRole(alice.getId(), profile.getId(), "writer");

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
        
    
        widgetImpl.createWidgetForPage(123, head123);
        widgetImpl.createWidgetForPage(234, post234);
        widgetImpl.createWidgetForPage(345, head345);
        widgetImpl.createWidgetForPage(345, intro456);
        widgetImpl.createWidgetForPage(345, image345);
        widgetImpl.createWidgetForPage(456, video456);
        
        System.out.println("Create Success!");
        
		
		// update developer
		ArrayList<Phone> charliePhones = new ArrayList<>();
		charliePhones.add(new Phone(0, "333-444-5555", 1, charlie.getId()));
		charlie.setPhones(charliePhones);
		developerImpl.updateDeveloper(charlie.getId(), charlie);
		
		// updatge widget
		head345.setOrder(3);
		intro456.setOrder(1);
		image345.setOrder(2);
		widgetImpl.updateWidget(head345.getId(),head345);
		widgetImpl.updateWidget(intro456.getId(),intro456);
		widgetImpl.updateWidget(image345.getId(),image345);
		
		// update page
		Collection<Page> pages = pageImpl.findPagesForWebsite(cnet.getId());
		for(Page page : pages) {
			page.setTitle("CNET - " + page.getTitle());
			pageImpl.updatePage(page.getId(), page);
		}
		
		// update roles
		roleImpl.deleteWebsiteRole(bob.getId(), cnet.getId(), "owner");
		roleImpl.deleteWebsiteRole(charlie.getId(), cnet.getId(), "editor");
		roleImpl.assignPageRole(bob.getId(), cnet.getId(), "editor");
		roleImpl.assignPageRole(charlie.getId(), cnet.getId(), "owner");
		
		System.out.println("Update Success");
		
		// delete developer
		ArrayList<Address> aliceAddresses = alice.getAddresses();
		if(aliceAddresses != null) 
			aliceAddresses.removeIf(x -> x.getPrimary() == 1);
		developerImpl.updateDeveloper(alice.getId(), alice);
		
		// delete widget
		Collection<Widget> widgets = widgetImpl.findAllWidgets();
		int maxOrder = -1, wid = -1;
		for(Widget w : widgets) {
			if(maxOrder < w.getOrder()) {
				maxOrder = w.getOrder();
				wid = w.getId();
			}
		}
		if(wid != -1)
			widgetImpl.deleteWidget(wid);
		
		// delete page
		pages = pageImpl.findPagesForWebsite(wikipedia.getId());
		Date latest = null;
		int pid = -1;
		for(Page p : pages) {
			if(pid == -1 || latest.compareTo(p.getUpdated()) < 0) {
				latest = p.getUpdated();
				pid = p.getId();
			}
		}
		//System.out.println("11111111111111111111111 " + pid);
		for(Widget w : widgetImpl.findWidgetsForPage(pid)) {
			widgetImpl.deleteWidget(w.getId());
		}
		roleImpl.deletePageRoleByPage(pid);
		pageImpl.deletePage(pid);
		
		// delete website
		pages = pageImpl.findPagesForWebsite(cnet.getId());
		for(Page p : pages) {
			for(Widget w : widgetImpl.findWidgetsForPage(p.getId())) {
				widgetImpl.deleteWidget(w.getId());
			}
			roleImpl.deletePageRoleByPage(p.getId());
			pageImpl.deletePage(p.getId());
		}
		roleImpl.deleteWebsiteRoleByWebsite(cnet.getId());
		websiteImpl.deleteWebsite(cnet.getId());
		
		System.out.println("Delete Success");
		System.out.println("All Done!");
	}

}
