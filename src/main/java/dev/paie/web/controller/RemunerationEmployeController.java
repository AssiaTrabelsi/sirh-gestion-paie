package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.GradeService;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	@Autowired
	ProfilRepository profilRepository;
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository ;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("entreprise", entrepriseRepository.findAll());
		mv.addObject("ProfilRemuniration", profilRepository.findAll());
		mv.addObject("grade", gradeRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String AjouterEmployer(@RequestParam("grade_id") Integer grade_id,
			@RequestParam("profil_id") Integer profil_id, @RequestParam("entreprise_id") Integer entreprise_id,
			@RequestParam("matricule") String matricule) {
		RemunerationEmploye remunerationEmploye = new RemunerationEmploye();
		remunerationEmploye.setMatricule(matricule);
		remunerationEmploye.setGrade(gradeRepository.findOne(grade_id));
		remunerationEmploye.setProfilRemuneration(profilRepository.findOne(profil_id));
		remunerationEmploye.setEntreprise(entrepriseRepository.findOne(entreprise_id));
         
		remunerationEmployeRepository.save(remunerationEmploye);
		
		return "redirect:lister";

	}
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("employes", remunerationEmployeRepository.findAll());
		return mv;
	}
 
	
	
}
