package com.github.rich.grails

import java.util.Date;

class Post {

    static constraints = {
		title()
		summary(maxSize:1000)
		dateCreated()
		lastUpdated()
	}

	String title
	String summary
	Date dateCreated
	Date lastUpdated
	
}
