package com.win.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.win.category.CategoryService;
import com.win.lorem.LoremService;
import com.win.people_last_name.PeopleLastNameService;
import com.win.services.MyUtil;
import com.win.services.RandomUtil;

@Repository
public class ShopRepository {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	PeopleLastNameService peopleLastNameService;
	@Autowired
	LoremService loremService;
	
	public List<Shop> randomSearch(Integer count){
		List<Shop> shops = new ArrayList<Shop>();
		for (int i=0;i<count;i++) {
			shops.add(createShop());
		}
		return shops;
	}
	
	private Shop createShop() {
		return Shop
				.builder()
				.companyName(getCompanyName())
				.registry(getNewCNPJ())
				.tradeName(getTradeName())
				.build();
	}
	
	private String getCompanyName() {		
		String names = peopleLastNameService
				.randomSearch(2)
				.stream()	
				.map(p -> StringUtils.capitalize(p.getLastName().toLowerCase()))
				.collect(Collectors.joining(" & "));
		String category = categoryService.randomSearch(1).get(0).getName();
		return String.format("%s, %s LTDA", names, category);		
	}
	
	private String getNewCNPJ() {
		String cnpj = "";
		char chars[] = "XX.XXX.XXX/0001-".toCharArray();
		for (char c : chars) {
			if (c == 'X')
				cnpj += RandomUtil.getRandomInt(10).toString();
			else
				cnpj += c;
		}
		String cleanCnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
		cnpj += MyUtil.getVerifuingDigitCNPJ(cleanCnpj);
		return cnpj;
	}
	
	private String getTradeName() {
		return loremService
				.getLoremForWords(RandomUtil.getRandomInt(2, 4), false)
				.replace(",", "")
				.replace(".", "")
				.toUpperCase();
	}

}
