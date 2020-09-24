package rcp3.x;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.print.attribute.standard.DocumentName;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class ActionDialog extends Action implements IWorkbenchAction{
	
	private static final String ID = "RCP3.x.ActionDialog";
	
	

	public ActionDialog() {
		super();
		// TODO Auto-generated constructor stub
		setId(ID);
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			   
			FileInputStream fis = new FileInputStream("Dog.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Object obj = ois.readObject();
			
			DogShowModel dog = (DogShowModel) obj;
			
			dog.toString();
			
			String dName = dog.getDogName();
			String breed = dog.getDogBreed();
			String[] category = dog.getCategories();
			String owner = dog.getOwnerName();
			String phno = dog.getPhno();
			
			
			
			Shell shell  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			
			String dialogBoxTitle = "Dialog Box";
			
			String Message = "DogName : " + dName + "\nDog Breed : "+ breed 
					+ "\nOwner Name : " + owner + "\nPhone : " + phno ;
			
			MessageDialog.openInformation(shell, dialogBoxTitle, Message);
			
			
			
			ois.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		
		
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
