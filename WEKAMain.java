//Author: Grace Olson
//AI WEKA Project
package AIWEKA;
import java.util.Arrays;
import java.util.Scanner;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.DecisionStump;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.pmml.Array;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.gui.Loader;

public class WEKAMain {

	public static void main(String args[]) throws Exception {
		DataSource source = new DataSource("insurance.csv");
		Instances data = source.getDataSet();
		// setting class attribute if the data format does not provide this information
		// For example, the XRFF format saves the class attribute information as well
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
		
		Normalize normalize = new Normalize();
		LinearRegression LRModel = new LinearRegression();
		DecisionStump DSModel = new DecisionStump();
		
		//from file
		Instances newData = normalization(data, normalize);
		linearRegressionMethod(newData, LRModel);
		decisionStumpMethod(newData, DSModel);
		
		//from user
		Instance userData = inputMethod(newData);
		normalize.input(userData);
		Instance newUserData = normalize.output();
		System.out.println("Here is your filtered input: " + newUserData.toString());
		System.out.println("Linear Regression Prediction: " + (LRModel.classifyInstance(newUserData)));
		System.out.println("Decision Stump Prediction: " + (DSModel.classifyInstance(newUserData)));
		
		//normalize user input
		//run LR and DS on user input
	}
	
	//file data methods:
	
	public static Instances normalization(Instances data, Normalize normalize) throws Exception {
		String [] normalizationOps = new String[4];
		normalizationOps[0] = "-T";
		normalizationOps[1] = "0.0";
		normalizationOps[2] = "-S";
		normalizationOps[3] = "1.0";
	
		normalize.setOptions(normalizationOps);
		normalize.setInputFormat(data);
		
		Instances newData = Filter.useFilter(data, normalize);
		return newData;
	}
	
	public static void linearRegressionMethod(Instances newData, LinearRegression LRModel) throws Exception{
		
		String [] linearRegressionOps = new String[2];
		linearRegressionOps[0] = "-S";
		linearRegressionOps[1] = "0";
		
		LRModel.setOptions(linearRegressionOps);
		LRModel.buildClassifier(newData);
		System.out.println(LRModel.toString());
	}
	
	public static void decisionStumpMethod(Instances newData, DecisionStump DSModel) throws Exception{
		
		DSModel.buildClassifier(newData);
		System.out.println(DSModel.toString());
	}
	
	public static Instance inputMethod(Instances data) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter age: ");
		double age = scanner.nextDouble();
		
		System.out.println("Enter sex (-1 for female, 1 for male): ");
		double sex = scanner.nextDouble();
		
		System.out.println("Enter BMI: ");
		double BMI = scanner.nextDouble();
		
		System.out.println("Enter number of children: ");
		double numberChild = scanner.nextDouble();
		
		System.out.println("Enter smoker (0 for no, 1 for yes): ");
		double smoker = scanner.nextDouble();
		
		System.out.println("Enter southwest region (1 for yes, 0 for no): ");
		double SWRegion = scanner.nextDouble();
		
		System.out.println("Enter southeast region (1 for yes, 0 for no): ");
		double SERegion = scanner.nextDouble();
		
		System.out.println("Enter northwest region (1 for yes, 0 for no): ");
		double NWRegion = scanner.nextDouble();
		
		System.out.println("Enter northeast region (1 for yes, 0 for no): ");
		double NERegion = scanner.nextDouble();
		
		scanner.close();

		
		Instance userData = new DenseInstance(data.numAttributes());
		userData.setDataset(data);
		
		userData.setValue(0, age);
		
		userData.setValue(1, sex);
		
		userData.setValue(2, BMI);
		
		userData.setValue(3, numberChild);
		
		userData.setValue(4, smoker);
		
		userData.setValue(5, SWRegion);

		userData.setValue(6, SERegion);
		
		userData.setValue(7, NWRegion);
		
		userData.setValue(8, NERegion);
		
		userData.setValue(9, 0);
	
		System.out.println("Here is your input: " + userData.toString());
		return userData;
		
	}
	

}
