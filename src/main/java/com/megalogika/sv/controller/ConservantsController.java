package com.megalogika.sv.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.megalogika.sv.model.E;
import com.megalogika.sv.service.EService;
import com.megalogika.sv.util.SuggestEntry;

@Controller
public class ConservantsController {
	
	@Autowired
	private EService eService;

	@RequestMapping("/conservants")
	public ModelMap ownerHandler(@RequestParam("q") String q) {
		Assert.notNull(q);
		
		ModelMap m = new ModelMap();
		
		List<SuggestEntry> suggestList = new ArrayList<SuggestEntry>();
		for(Iterator<E> i = getEService().findConservants(q.toLowerCase()).iterator(); i.hasNext();) {
			suggestList.add(new SuggestEntry(i.next()));
		}
		
		m.addAttribute("jsonList", JSONSerializer.toJSON(suggestList).toString());
		return m;
	}

	public void setEService(EService eService) {
		this.eService = eService;
	}

	public EService getEService() {
		return eService;
	}

}
