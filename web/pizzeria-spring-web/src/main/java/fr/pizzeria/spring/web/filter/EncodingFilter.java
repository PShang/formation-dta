package fr.pizzeria.spring.web.filter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/mvc/*")
public class EncodingFilter {
	public EncodingFilter() {
        super();
    }
}
