package rcp3.x;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.inject.Inject;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import rcp3.x.DogShowModel;

public class DogShowController extends ViewPart implements IPerspectiveFactory{
	
	public static final String ID = "RCP3.x.DogShowController";

	@Inject IWorkbench workbench;
	
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	ScrolledComposite scrolledComposite;
	
	Image dogImage;
	
	Display display;
	
	DogShowModel model;
	
	Text dogName;
	
	Combo dogBreed;
	
	List categories;
	
	Text ownerName;
	
	Text ownerPhone;
	
	IPageLayout layout;

	
	public DogShowController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		
		// shell.setText("Dog Show Entry");
		
		DogShowModel model = new DogShowModel();
		
		GridLayout gridLayout = new GridLayout();
		
		 gridLayout.numColumns = 3;
		 parent.setLayout(gridLayout);

		 parent.setLayout(gridLayout);
		 
		 // Create the ScrolledComposite to scroll horizontally and vertically
		 
		 ScrolledComposite scrolledcomposite = new ScrolledComposite(parent, SWT.BORDER| SWT.H_SCROLL| SWT.V_SCROLL);
		 scrolledcomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		 // Create a child composite to hold the controls
		 Composite child = new Composite(scrolledcomposite, SWT.NONE);
		 child.setLayout(gridLayout);
		
		 

		 // Setting a title
	       
	       	Label label1 = new Label(child, SWT.NULL);
	       
	       	Font font = new Font(label1.getDisplay(),  new FontData("Mono", 10, SWT.BOLD));
	       	label1.setFont(font);
	       
	       	label1.setText("-------------  Dog Show Entry  --------------");
	       
	       	GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);

	       	gridData.horizontalSpan = 3;
	       
	       	label1.setLayoutData(gridData);

		   
		      //Dog's name
		      new Label(child, SWT.NULL).setText("Dog's Name:");
		      dogName = new Text(child, SWT.SINGLE | SWT.BORDER);
		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		      gridData.horizontalSpan = 2 ;
		      dogName.setLayoutData(gridData);
		      
		    

		      //Breed
		      new Label(child, SWT.NULL).setText("Breed:");
		      dogBreed = new Combo(child, SWT.NULL);
		      dogBreed.setItems(new String [] {"Collie", "Pitbull", "Poodle", "Scottie"});
		      dogBreed.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		      
		     
		      //categories
		      Label label = new Label(child, SWT.NULL);
		      label.setText("Categories");
		      label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));
		      

		     
		      //photo
		      new Label(child, SWT.NULL).setText("Photo:");
		      Canvas dogPhoto = new Canvas(child, SWT.BORDER);
		      gridData = new GridData(GridData.FILL_BOTH);
		      gridData.widthHint = 80;

		      gridData.heightHint = 80;

		      gridData.verticalSpan = 3;

		      dogPhoto.setLayoutData(gridData);


		      dogPhoto.addPaintListener(new PaintListener() {

		         public void paintControl(final PaintEvent event) {

		             if (dogImage != null) {

		                event.gc.drawImage(dogImage, 0, 0);

		             }

		         }

		      });



		     categories = new List(child, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);

		      categories.setItems(new String [] {

		         "Best of Breed", "Prettiest Female", "Handsomest Male",

		         "Best Dressed", "Fluffiest Ears", "Most Colors",

		         "Best Performer", "Loudest Bark", "Best Behaved",

		         "Prettiest Eyes", "Most Hair", "Longest Tail",

		         "Cutest Trick"});

		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);

		      gridData.verticalSpan = 4;

		      int listHeight = categories.getItemHeight() * 12;

		      Rectangle trim = categories.computeTrim(0, 0, 0, listHeight);

		      gridData.heightHint = trim.height;

		      categories.setLayoutData(gridData);
		      
		     
		     
		      //Browse
		      Button browse = new Button(child, SWT.PUSH);

		      browse.setText("Browse...");

		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

		      gridData.horizontalIndent = 5;

		      browse.setLayoutData(gridData);


		      browse.addSelectionListener(new SelectionAdapter() {

		         public void widgetSelected(SelectionEvent event) {
 
					String fileName = new FileDialog(shell).open();

		             if (fileName != null) {

		                dogImage = new Image(display, fileName);

		             }
		             
		             dogPhoto.redraw();

		         }

		      });

		     
		      //DELETE
		      Button delete = new Button(child, SWT.PUSH);

		      delete.setText("Delete");

		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING);

		      gridData.horizontalIndent = 5;

		      delete.setLayoutData(gridData);


		      delete.addSelectionListener(new SelectionAdapter() {

		         public void widgetSelected(SelectionEvent event) {

		             if (dogImage != null) {

		                dogImage.dispose();

		                dogImage = null;

		                dogPhoto.redraw();

		             }

		         }

		      });

		     
		      //Owner Information
		      Group ownerInfo = new Group(child, SWT.NULL);

		      ownerInfo.setText("Owner Info");

		      gridLayout = new GridLayout();

		      gridLayout.numColumns = 2;

		      ownerInfo.setLayout(gridLayout);

		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);

		      gridData.horizontalSpan = 2;

		      ownerInfo.setLayoutData(gridData);


		     
		      //owner name
		      new Label(ownerInfo, SWT.NULL).setText("Name:");
		      ownerName = new Text(ownerInfo, SWT.SINGLE | SWT.BORDER);
		      ownerName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));


		     
		      //owner phone
		      new Label(ownerInfo, SWT.NULL).setText("Phone:");
		      ownerPhone = new Text(ownerInfo, SWT.SINGLE | SWT.BORDER);
		      ownerPhone.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		      
		      


		     //Enter Button

		      Button enter = new Button(child, SWT.PUSH);

		      enter.setText("Enter");

		      gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);

		      gridData.horizontalSpan = 1;

		      enter.setLayoutData(gridData);

		      enter.addSelectionListener(new SelectionAdapter() {

		         public void widgetSelected(SelectionEvent event) {
		         
		         String DogName = dogName.getText();
		         String DogBreed = dogBreed.getText();
		         String OwnerName = ownerName.getText();
		         String OwnerPhone = ownerPhone.getText();
		         String[] Categories = categories.getSelection();
		         
		         
		         model.setDogName(DogName);
		         model.setDogBreed(DogBreed);
		         model.setOwnerName(OwnerName);
		         model.setPhno(OwnerPhone);
		         model.setCategories(Categories);
		         
		         
		         DogShowModel dse = new DogShowModel(DogName, DogBreed, OwnerName, OwnerPhone, Categories);
		         
		         System.out.println(dse);
		         
		         try {
		         
		        	 FileOutputStream fos = new FileOutputStream("Dog.txt");
		        	 
		        	 ObjectOutputStream oos = new ObjectOutputStream(fos);
		        	 
		        	 oos.writeObject(dse);
		        	 
		        	 System.out.println("Dog Details added successfully to file");
		        	 

		         } catch (FileNotFoundException e) {
		        	 // TODO Auto-generated catch block
		        	 e.printStackTrace();
		         } catch (IOException e) {
		        	 // TODO Auto-generated catch block
		        	 e.printStackTrace();
		         }
		        
		         }

		      });
		      
		      
		      	// Read Button creation
		       
		       Button read = new Button(child, SWT.PUSH);
		       
		       read.setText("Read");
		       
		       read.addSelectionListener(new SelectionAdapter() {
		    	   
		    	   public void widgetSelected(SelectionEvent event) {
		    		   
		    		   try {
		    			   
						FileInputStream fis = new FileInputStream("Dog.txt");
						ObjectInputStream ois = new ObjectInputStream(fis);
						
						Object obj = ois.readObject();
						
						DogShowModel dog = (DogShowModel) obj;
						
						dog.toString();
						
						String owner = dog.getOwnerName();
						
						String oName = ownerName.getText();
						
						if(owner.equalsIgnoreCase(oName)) {
							
							dogName.setText(dog.getDogName());
		    	   			dogBreed.setText(dog.getDogBreed());
		    	   			ownerName.setText(dog.getOwnerName());
		    	   			ownerPhone.setText(dog.getPhno());
							
						}else {
							

							dogName.setText("Data Not Found");
		    	   			dogBreed.setText("Data Not Found");
		    	   			// ownerName.setText("Data Not Found");
		    	   			ownerPhone.setText("Data Not Found");
							
						}
						
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
			});
		       
		    
		       
		    /*Dialog Button creation
		       
		       Button dialog = new Button(child, SWT.PUSH);
		       
		       dialog.setText("getDialog");
		       
		       dialog.addSelectionListener(new SelectionAdapter() {
		    	   
		    	   public void widgetSelected(SelectionEvent event) {
		    		   
		    		CustomDialog dialog = new CustomDialog(shell);
		    		
		    		dialog.open();
		    		dialog.configureShell(shell);
		    		dialog.createDialogArea(parent);
		    	 }
			});*/

		       

		    if (dogImage != null) {

		        dogImage.dispose();

		     }

		      // Set the child as the scrolled content of the ScrolledComposite
		      scrolledcomposite.setContent(child);

		      // Set the minimum size
		      scrolledcomposite.setMinSize(400, 400);

		      // Expand both horizontally and vertically
		      scrolledcomposite.setExpandHorizontal(true);
		      scrolledcomposite.setExpandVertical(true);
		      
		      
		      // creating actions 
		      
		      ActionDialog dialog1 = new ActionDialog();
		      dialog1.setText("show");
		      getViewSite().getActionBars().getMenuManager().add(dialog1);
		      
		      
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createInitialLayout(IPageLayout layout) {
		// TODO Auto-generated method stub
		layout.getViewLayout(ID).setCloseable(false);
	}


}
