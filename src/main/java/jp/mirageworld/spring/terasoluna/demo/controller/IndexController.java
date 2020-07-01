package jp.mirageworld.spring.terasoluna.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping
	@PostMapping
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("serverTime", now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		return "welcome/home";
	}

}
