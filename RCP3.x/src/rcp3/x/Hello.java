package rcp3.x;

import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.handlers.HandlerUtil;

public class Hello extends AbstractHandler{
	
	public static final String ID = "RcpView.command";
	
	@Inject IWorkbench workbench;
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), 
									"Welcome", "Hello ......  Welcome!!!");
		
		return null;
	}

}
