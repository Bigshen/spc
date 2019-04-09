package com.foxlink.spc.pojo;

import java.util.List;

public class EleScaleMeasure {
	private String WorkShop;
	private String Inspection_Item;
	private String Nominal_Dim;
	private String Upper_Dim;
	private String Lower_Dim;
	private String Frequency;
	private List<String> ADim1_array;
	private List<String> BDim1_array;
	private List<String> Dim1_array;
	private String Result;
	private String REMARK1;

	public String getWorkShop() {
		return WorkShop;
	}
	public void setWorkShop(String workShop) {
		WorkShop = workShop;
	}
	public String getInspection_Item() {
		return Inspection_Item;
	}
	public void setInspection_Item(String inspection_Item) {
		Inspection_Item = inspection_Item;
	}
	public String getNominal_Dim() {
		return Nominal_Dim;
	}
	public void setNominal_Dim(String nominal_Dim) {
		Nominal_Dim = nominal_Dim;
	}
	public String getUpper_Dim() {
		return Upper_Dim;
	}
	public void setUpper_Dim(String upper_Dim) {
		Upper_Dim = upper_Dim;
	}
	public String getLower_Dim() {
		return Lower_Dim;
	}
	public void setLower_Dim(String lower_Dim) {
		Lower_Dim = lower_Dim;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}

	public List<String> getADim1_array() {
		return ADim1_array;
	}

	public void setADim1_array(List<String> ADim1_array) {
		this.ADim1_array = ADim1_array;
	}

	public List<String> getBDim1_array() {
		return BDim1_array;
	}

	public void setBDim1_array(List<String> BDim1_array) {
		this.BDim1_array = BDim1_array;
	}

	public List<String> getDim1_array() {
		return Dim1_array;
	}

	public void setDim1_array(List<String> dim1_array) {
		Dim1_array = dim1_array;
	}

	public String getREMARK1() {
		return REMARK1;
	}

	public void setREMARK1(String REMARK1) {
		this.REMARK1 = REMARK1;
	}
	@Override
	public String toString() {
		return "Measure [WorkShop=" + WorkShop + ", Inspection_Item=" + Inspection_Item + ", Nominal_Dim=" + Nominal_Dim
				+ ", Upper_Dim=" + Upper_Dim + ", Lower_Dim=" + Lower_Dim + ", Frequency=" + Frequency +
				", ADim1_array=" + ADim1_array + ","+"BDim1_array="+BDim1_array+", Dim1_array="+Dim1_array+", Result=" + Result + ", REMARK1=" +REMARK1+"]";
	}

}
