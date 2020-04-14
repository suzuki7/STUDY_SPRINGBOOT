package com.tuyano.springboot;

import java.util.ArrayList;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.tuyano.springboot.repositories.MyDataRepository;

@Controller
public class HelloController {

	@Autowired
	MyDataRepository repository;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav){
		mav.setViewName("index");
		mav.addObject("msg", "test");
		mav.addObject("formModel",mydata);
		Iterable<MyData> list =repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}
	
	@RequestMapping(value="/", method= RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel") 
			@Validated MyData mydata,
			BindingResult result,
			ModelAndView mov) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
		repository.saveAndFlush(mydata);
		res =new ModelAndView("redirect:/");
		}else {
			mov.setViewName("index");
			mov.addObject("msg","sorry,error is occured...");
			Iterable<MyData> list = repository.findAll();
			mov.addObject("detalist",list);
			res = mov;
		}
		return res;
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata,@PathVariable int id , ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","edit mydata");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		
		return mav;
	}
		
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute MyData mydata,ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}
		

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id , ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","delete mydata");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		
		return mav;
	}
		
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView remove(@RequestParam long id,ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
		

	
	
	@PostConstruct
	public void init() {
		//1つ目のダミーデータ作成
		MyData d1 = new MyData();
		d1.setName("tuyano");
		d1.setAge(34);
		d1.setMail("aaa.com@docomo.ne.jp");
		d1.setMemo("this is my data");
		repository.saveAndFlush(d1);
		//2つ目のダミーデータ作成
		MyData d2 = new MyData();
		d2.setName("suzuki");
		d2.setAge(35);
		d2.setMail("yyy.com@docomo.ne.jp");
		d2.setMemo("yuzu?");
		repository.saveAndFlush(d2);		
		//3つ目のダミーデータ作成
		MyData d3 = new MyData();
		d3.setName("asuka");
		d3.setAge(27);
		d3.setMail("zzzzz.com@docomo.ne.jp");
		d3.setMemo("nase");
		repository.saveAndFlush(d3);
	}
	

}
	
//	@RequestMapping(value="/{num}")
//	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
//		mav.setViewName("index");
//		mav.addObject("num", num);
//		if(num >=0) {
//			mav.addObject("check","num >= data.size() ? 0 : num");
//		}else {
//			mav.addObject("check","num <= data.size() * -1 ? 0 : num * -1");
//		}
//		ArrayList<DataObject> data = new ArrayList<>();
//		data.add(new DataObject (1,"@aaa","123"));
//		data.add(new DataObject (2,"@tta","444"));
//		data.add(new DataObject (3,"@yya","666"));
//		mav.addObject("data", data);		
//		
//		return mav; 
//	}
//}
//
//class DataObject{
//	private int id;
//	private String name;
//	private String value;
//	
//	
//	public DataObject(int id,String name,String value) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.value = value;
//		
//	}
//		public int getId() {
//			return id;
//		}
//		public void setId(int id) {
//			this.id = id;
//		}
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getValue() {
//			return value;
//		}
//		public void setValue(String value) {
//			this.value = value;
//		}
//}
//	
//@RequestMapping("/")
//	public ModelAndView index(ModelAndView mav) {
//		mav.setViewName("index");
//		ArrayList<String[]> data = new ArrayList<>();
//		data.add(new String[] {"tara","@aaa","123"});
//		data.add(new String[] {"baba","@tta","444"});
//		data.add(new String[] {"yyya","@yya","666"});
//		mav.addObject("data", data);
//		return mav;
//	}
//}

	
	
//@RequestMapping(value="/")
//public ModelAndView index(ModelAndView mav) {
//	mav.setViewName("index");
//	mav.addObject("msg", "current data.");
//	DataObject obj = new DataObject(123,"hanako","hanako@flower");
//	mav.addObject("object", obj);
//	
//	mav.addObject("test","message 1 <hr/> message 2 <br/> message 3");
//	return mav;
//}
//
//

//}
//
//	
	
	
	
	
	
//	@RequestMapping(value="/",method=RequestMethod.GET)
//	public ModelAndView index(ModelAndView mav) {
//		mav.addObject("msg", "フォームを送信してください");
//		mav.setViewName("index");
//		return mav;
//	}
//	
//	@RequestMapping(value="/",method=RequestMethod.POST)
//	public ModelAndView send(
//		@RequestParam(value="check1",required=false) boolean check1,
//		@RequestParam(value="radio1",required=false) String redio1,
//		@RequestParam(value="select1",required=false) String select1,
//		@RequestParam(value="select2",required=false) String[] select2,
//		ModelAndView mav) {
//		
//		String res="";
//		
//		try {
//		res = "check:"+check1+" radio:"+redio1+" select:"+select1+
//				"\nselect2";
//		
//		for(int i =0 ;i<select2.length;i++) {
//			res +=","+select2[i];
//		}
//		}catch (Exception e) {
//			res +=null;
//		}
//				
//				
//		mav.addObject("msg",res);
//		mav.setViewName("index");
//		return mav;
//	}

	
//	@RequestMapping("/{num}")
//	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
//		int res = 0;
//		for(int i = 1;i<=num;i++) {
//			res +=i;}
//		mav.addObject("msg", "total:" + res);
//		mav.setViewName("index");
//		return mav;
//	  }
//	}
	
//	@RequestMapping("/{num}")
//	public String index(@PathVariable int num, Model model) {
//		int res = 0;
//		for(int i = 1;i<=num;i++) {
//			res +=i;
//			model.addAttribute("msg","total:" + res);
//		}
//		return "index";
//	}
	





