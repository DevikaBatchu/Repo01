package rcp3.x;


import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenHandler {

	public static void execute(Shell shell){
		FileDialog dialog = new FileDialog(shell);
		dialog.open();
	}
}
