package employee01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class EmpShow extends Action implements IWorkbenchAction{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private static final String ID = "employee.EmpShow";
	
	

	public EmpShow() {
		super();
		// TODO Auto-generated constructor stub
		setId(ID);
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			   
			FileInputStream fis = new FileInputStream("D:\\Git\\Repo01\\Employee01\\src\\employee01\\Emp.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Object obj = ois.readObject();
			
			Employee emp = (Employee) obj;
			
			emp.toString();
			
			int eId = emp.geteId();
			String eName = emp.geteName();
			int age = emp.getAge();
			double salary = emp.getSalary();
			String gender = emp.getGender();
			
			
			Shell shell  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			
			String dialogBoxTitle = " Action Dialog Box";
			
			String Message = "EmpId :"  + eId + "\nEmpName : " + eName + "\nEmpAge : "+ age 
					+ "\nSalary : " + salary + "\nGender : " + gender;
			
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


}
