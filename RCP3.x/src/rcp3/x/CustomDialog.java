package rcp3.x;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class CustomDialog extends Dialog{
	
	DogShowModel model;
	DogShowController controller;

	protected CustomDialog(Shell parentShell) {
		
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		
		Composite container = (Composite) super.createDialogArea(parent);
		
		GridLayout layout = new GridLayout(2, false);
		
		container.setLayout(layout);
		
		
		model = new DogShowModel();
		
		controller = new DogShowController();
	
		
		System.out.println("DogName : " + controller.dogName.getText());
		System.out.println("Dog Breed : " + model.getDogBreed());
		System.out.println("OwnerName : " + model.getOwnerName());
		System.out.println("Phone Number : " + model.getPhno());
		System.out.println("Dog Category : " + model.getCategories());
		
		
		
		createDogName(container);
		createDogBreed(container);
		createDogCategory(container);
		createOwnerName(container);
		createphno(container);
		
		
		
		
		return container;
	}
	
	private void createDogName(Composite container) {
		// TODO Auto-generated method stub
		new Label(container, SWT.NULL).setText("Dog's OwnerPhno:");
		
		String dName =controller.dogName.toString(); 
		new Label(container, SWT.NULL).setText(dName);
		
	}
	
	
	private void createDogBreed(Composite container) {
		// TODO Auto-generated method stub
		new Label(container, SWT.NULL).setText("Dog's OwnerName:");
		new Label(container, SWT.NULL).setText(model.getDogBreed());
		
	}
	
	private void createDogCategory(Composite container) {
		// TODO Auto-generated method stub
		new Label(container, SWT.NULL).setText("Dog's Category:");
		
		String cat = model.getCategories().toString();
		new Label(container, SWT.NULL).setText(cat);
		
	}

	
	
	private void createphno(Composite container) {
		// TODO Auto-generated method stub
		new Label(container, SWT.NULL).setText("Dog's Name:");
		new Label(container, SWT.NULL).setText(model.getPhno());
		
	}

	private void createOwnerName(Composite container) {
		// TODO Auto-generated method stub
		new Label(container, SWT.NULL).setText("Dog's Breed:");
		new Label(container, SWT.NULL).setText(model.getOwnerName());
		
	}

	

	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		
		newShell.setText("Dialog Box");
	}
	
}
