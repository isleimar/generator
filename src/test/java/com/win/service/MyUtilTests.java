package com.win.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.win.services.MyUtil;

class MyUtilTests {
	
	@Test
	void cnpjTest() {
		String cnpjsValid[] = {
				"76101046000169", "43.794.910/0001-04", "55.531.287/0001-78",
				"04.779.031/0001-43", "06.796.160/0001-75", "28490970000131",
				"72.528.280/0001-16", "77.855.158/0001-78", "13.283.004/0001-76",
				"75.003.147/0001-34", "07.975.752/0001-17", "44.412.690/0001-61",
				"89.191.360/0001-21", "18.401.940/0001-58", "00.000.000/0001-91",
				
		};
		String cnpjsInvalid[] = {
				"101046000169", "4A.794.910/0001-04", "55.531.287/0001-00",
				"047790310001433242342", "0612313123123", "2849097000013",
				"72.528.280/0001-13", "77.855.158/0001-71", "13.283.004/0001-78",
				"75.003.147/0001-3", "07.975.752/0001-07", "44.412.690/0001-69",
				"893191.360/0001-22", "182401.940/0001-00", "00.000.000/0001-00",
				
		};
		for (String cnpj : cnpjsValid) {
			assertThat(MyUtil.validateCNPJ(cnpj)).isEqualTo(true); 			
		}
		for (String cnpj : cnpjsInvalid) {			
			assertThat(MyUtil.validateCNPJ(cnpj)).isEqualTo(false); 			
		}		
	}

}
