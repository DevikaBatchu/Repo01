package rcp3.x;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;


import rcp3.x.Employee;

public class EmpView01 extends ViewPart{
	
	public static final String ID = "RCP3.x.EmpView01";

	@Inject IWorkbench workbench;
	
	private TableViewer v;
	
	private static TableViewerColumn createColumnFor(TableViewer viewer, String label, int style) {
		
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		
		column.getColumn().setWidth(100);
		column.getColumn().setText(label);
		column.getColumn().setMoveable(true);
		column.getColumn().getResizable();
		
		return column;
		
	}
	
	protected abstract class AbstractEditingSupport extends EditingSupport {
		
		private TextCellEditor editor;

		public AbstractEditingSupport(TableViewer viewer) {
			super(viewer);
			this.editor = new TextCellEditor(viewer.getTable());
		}

		public Color getForeground(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		public Color getBackground(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected void setValue(Object element, Object value) {
			doSetValue(element, value);
			getViewer().update(element, null);
		}

		protected abstract void doSetValue(Object element, Object value);
	}
	
	
	Employee emp = new Employee();
	

	@Override
	public void createPartControl(Composite parent) {
		
		// TODO Auto-generated method stub
		
		v = new TableViewer(parent, SWT.BORDER| SWT.FULL_SELECTION);
		
		v.setContentProvider(ArrayContentProvider.getInstance());
		
		
		// Emp Id column
		TableViewerColumn column1 = createColumnFor(v, "EmpId", SWT.NONE);
		
		column1.setLabelProvider(new ColumnLabelProvider() {
			
			public String getText(Object element) {
				
				String eId = Integer.toString(((Employee)element).geteId());
				
				return eId;
		
			}
		});

		
		// CREATING Emp Name column
		TableViewerColumn column2 = createColumnFor(v, "EmpName", SWT.BOLD);
				
		column2.setLabelProvider(new ColumnLabelProvider() {
					
			public String getText(Object element) {
						
				return ((Employee)element).geteName();
			
			}
		});
		
		// set editing support for ename
		column2.setEditingSupport(new AbstractEditingSupport(v) {

			@Override
			protected Object getValue(Object element) {
				return ((Employee)element).geteName();
			}

			@Override
			protected void doSetValue(Object element, Object value) {
				
				String name = (String) value;
				((Employee) element).seteName(name);
			}
			
			@Override
			public Color getBackground(Object element) {
				// TODO Auto-generated method stub
				 if(((Employee) element).getSalary() >= 50000) {
					
					return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
					
				}
				return null;
			}
			
			@Override
			public Color getForeground(Object element) {
				// TODO Auto-generated method stub
				if(((Employee) element).getSalary() <= 50000) {
					
					return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
					
				}
				return null;
			}

		});
				
				
		// CREATING Emp age column
		TableViewerColumn column3 = createColumnFor(v, "EmpAge", SWT.BOLD);
				
		column3.setLabelProvider(new ColumnLabelProvider() {
					
			public String getText(Object element) {
						
				String eAge = Integer.toString(((Employee)element).getAge());
						
					return eAge;
					
			}
		});
				
		// CREATING Emp salary column
		TableViewerColumn column4 = createColumnFor(v, "EmpSalary", SWT.BOLD);
				
		column4.setLabelProvider(new ColumnLabelProvider() {
					
		public String getText(Object element) {
						
			String eSal = Double.toString(((Employee)element).getSalary());
						
				return eSal;
				
			}
		});
				
				
		Employee[] model = createModel();
		
		v.setInput(model);
		
		
		Table table = v.getTable();
		
		table.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent event) {
			
				

				TableItem[] items = table.getItems();
				
				ArrayList<Integer> selectedItemList = new ArrayList<Integer>();
				
				System.out.println(event.item.getData());
				
				
				try {
					
					FileOutputStream fos = new FileOutputStream("Emp.txt");
					
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					oos.writeObject(event.item.getData());
					
					System.out.println("Employee Details added successfully");
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
			
			
			
		});
		
		v.getTable().setLinesVisible(true);
		v.getTable().setHeaderVisible(true);
		
		EmpShow dialog1 = new EmpShow();
	    dialog1.setText("show");
	    getViewSite().getActionBars().getMenuManager().add(dialog1);
		
		
		
		/* Creating a remove button
		
		Button remove = new Button(parent, SWT.NONE);
		remove.setText("Select");
				
		GridData gData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
				
		remove.addSelectionListener(new SelectionAdapter() {
					
					
			public void widgetSelected(SelectionEvent event) {
						
						
				IStructuredSelection sElement = (IStructuredSelection) v.getSelection();
						
				v.remove(sElement);
			}
		});*/

		
		
	}
	
	
	private Employee[] createModel() {
		
		return new Employee[] {
				
				new Employee(101, "Abhi", 22, 45000, "Female"),
				new Employee(102, "Joshi", 33, 60000, "Female"),
				new Employee(103, "Mahi", 25, 55000, "Female"),
				new Employee(104, "Nivi", 28, 25000, "Female"),
				new Employee(106, "Mehak", 42, 54000, "Female")
		};
	}
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
		v.getControl().setFocus();
		
	}

}
