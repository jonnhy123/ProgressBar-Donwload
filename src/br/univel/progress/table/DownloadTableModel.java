package br.univel.progress.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DownloadTableModel extends AbstractTableModel {

	private List<Download> lista = new ArrayList<Download>();

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Origem";
		case 1:
			return "Progresso";
		case 2:
			return "Destino";
		}
		return "erro";
	}

	@Override
	public Object getValueAt(int row, int col) {
		Download d = lista.get(row);
		switch (col) {
		case 0:
			return d.getOrigem();
		case 1:
			return d.getProgresso();
		case 2:
			return d.getDestino();
		}
		return "erro";
	}

	public void adicionarDownloadNoModel(Download d) {
		this.lista.add(d);
		
		int row = this.lista.size() -1;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					for (int i = 0; i <= 100; i++) {
						d.setProgresso(i);
						fireTableCellUpdated(row, 1);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					for (int i = 100; i >= 0; i--) {
						d.setProgresso(i);
						fireTableCellUpdated(row, 1);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}).start();
		super.fireTableDataChanged();
	}

}


























       // fireTableCellUpdated(row, 1);









