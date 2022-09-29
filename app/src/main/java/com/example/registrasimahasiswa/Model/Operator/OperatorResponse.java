package com.example.registrasimahasiswa.Model.Operator;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OperatorResponse{

	@SerializedName("OperatorResponse")
	private List<OperatorResponseItem> operatorResponse;

	public void setOperatorResponse(List<OperatorResponseItem> operatorResponse){
		this.operatorResponse = operatorResponse;
	}

	public List<OperatorResponseItem> getOperatorResponse(){
		return operatorResponse;
	}
}