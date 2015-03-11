/*
 * 
 *               Panbox - encryption for cloud storage 
 *      Copyright (C) 2014-2015 by Fraunhofer SIT and Sirrix AG 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Additonally, third party code may be provided with notices and open source
 * licenses from communities and third parties that govern the use of those
 * portions, and any licenses granted hereunder do not alter any rights and
 * obligations you may have under such open source licenses, however, the
 * disclaimer of warranty and limitation of liability provisions of the GPLv3 
 * will apply to all the product.
 * 
 */
package org.panbox.desktop.common.gui;

import org.panbox.Settings;
import org.panbox.core.Utils;
import org.panbox.core.exception.RandomDataGenerationException;
import org.panbox.core.identitymgmt.VCardProtector;
import org.panbox.desktop.common.PanboxClient;
import org.panbox.desktop.common.gui.addressbook.ContactListModel;
import org.panbox.desktop.common.gui.addressbook.PanboxGUIContact;
import org.panbox.desktop.common.utils.DesktopApi;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author palige
 * 
 *         Dialog for exporting one or more identities to a file
 */
public class ExportIdentitiesDialog extends javax.swing.JDialog {

	private static final ResourceBundle bundle = ResourceBundle.getBundle(
			"org.panbox.desktop.common.gui.Messages", Settings.getInstance()
					.getLocale());

	/**
	 * 
	 */
	private static final long serialVersionUID = -1160716976951996434L;
	private SimpleDateFormat df;
	private PanboxClient client;
	private List<PanboxGUIContact> contacts;
	private ContactListModel contactModel;

	/**
	 * Creates new form ExportIdentitiesDialog
	 */
	public ExportIdentitiesDialog(PanboxClient client,
			List<PanboxGUIContact> contacts) {
		super(client.getMainWindow());
		this.client = client;
		this.contacts = contacts;
		this.df = new SimpleDateFormat("yyyy-MM-dd");
		this.contactModel = new ContactListModel();
		for (PanboxGUIContact c : contacts) {
			this.contactModel.addElement(c);
		}

		initComponents();

		DocumentListener d = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkExportability();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkExportability();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkExportability();
			}
		};

		exportPINTextField.getDocument().addDocumentListener(d);
		fileLocTextField.getDocument().addDocumentListener(d);
		genPINButton.setToolTipText(bundle.getString(getClass().getSimpleName()
				+ ".genPINButton.tooltip"));

	}

	/**
	 * enables export button if all prerequisites for export are being met.
	 * minimum PIN length is set to 4 characters.
	 */
	private void checkExportability() {
		if ((exportPINTextField.getText() == null)
				|| (exportPINTextField.getText().length() < 5)
				|| (fileLocTextField.getText() == null)
				|| fileLocTextField.getText().length() == 0) {
			exportButton.setEnabled(false);
			pinToClipboardCheckbox.setEnabled(false);
		} else {
			exportButton.setEnabled(true);
			pinToClipboardCheckbox.setEnabled(true);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		fileLocLabel = new javax.swing.JLabel();
		fileLocTextField = new javax.swing.JTextField();
		fileLocButton = new javax.swing.JButton();
		exportPINLabel = new javax.swing.JLabel();
		exportPINTextField = new javax.swing.JTextField();
		genPINButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		exportButton = new javax.swing.JButton();
		pinToClipboardCheckbox = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(bundle.getString("ExportIdentitiesDialog.title")); // NOI18N
		setModal(true);
		setResizable(false);

		fileLocLabel.setText(bundle.getString("ExportIdentitiesDialog.fileLocLabel")); // NOI18N

		fileLocTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fileLocTextFieldActionPerformed(evt);
			}
		});

		fileLocButton.setText(bundle.getString("ExportIdentitiesDialog.fileLocButton")); // NOI18N
		fileLocButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fileLocButtonActionPerformed(evt);
			}
		});

		exportPINLabel.setText(bundle.getString("ExportIdentitiesDialog.exportPINLabel")); // NOI18N

		genPINButton.setText(bundle.getString("ExportIdentitiesDialog.genPINButton")); // NOI18N
		genPINButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				genPINButtonActionPerformed(evt);
			}
		});

		cancelButton.setText(bundle.getString("ExportIdentitiesDialog.cancelButton")); // NOI18N
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		exportButton.setText(bundle.getString("ExportIdentitiesDialog.exportButton")); // NOI18N
		exportButton.setEnabled(false);
		exportButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});

		pinToClipboardCheckbox.setText(bundle.getString("ExportIdentitiesDialog.copyPINToClipboardCheckbox")); // NOI18N
		pinToClipboardCheckbox.setEnabled(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(fileLocLabel)
										.addComponent(exportPINLabel))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(0, 0, Short.MAX_VALUE)
												.addComponent(exportButton)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(cancelButton))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(pinToClipboardCheckbox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(fileLocTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
														.addComponent(exportPINTextField, javax.swing.GroupLayout.Alignment.LEADING))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(fileLocButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(genPINButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(fileLocLabel)
										.addComponent(fileLocTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(fileLocButton))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(genPINButton)
										.addComponent(exportPINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(exportPINLabel))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(pinToClipboardCheckbox)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cancelButton)
										.addComponent(exportButton))
								.addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void genPINButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_genPINButtonActionPerformed
		try {
			exportPINTextField.setText(String.valueOf(VCardProtector
					.generatePassword()));
		} catch (RandomDataGenerationException e) {
			JOptionPane.showMessageDialog(this,
					"Error during random data generation!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}// GEN-LAST:event_genPINButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
		this.dispose();
	}// GEN-LAST:event_cancelButtonActionPerformed

	private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exportButtonActionPerformed
		File vcardFile = new File(fileLocTextField.getText());

		if (vcardFile.exists()) {
			int res = JOptionPane.showConfirmDialog(this,
					bundle.getString("file.exists.do.you.want.to.overwrite"), bundle.getString("file.exists"),
					JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.NO_OPTION) {
				return;
			}
		} else {
			try {
				vcardFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!vcardFile.canWrite()) {
			JOptionPane.showMessageDialog(this, bundle.getString("cannot.write.file"), bundle.getString("error"),
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (exportPINTextField.getText() != null
					&& exportPINTextField.getText().length() != 0) {
				client.exportContacts(contacts, vcardFile,
						Utils.toUTF8String(exportPINTextField.getText())
								.toCharArray());
				if (pinToClipboardCheckbox.isSelected()) {
					DesktopApi.copyToClipboard(exportPINTextField.getText(), false);
				}
				this.dispose();
			}
		}
	}// GEN-LAST:event_exportButtonActionPerformed

	private void fileLocTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fileLocTextFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_fileLocTextFieldActionPerformed

	private void fileLocButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_fileLocButtonActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Zip archive (.zip)", "zip");
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setSelectedFile(new File(System.getProperty("user.home")
				+ File.separator + "panbox-identites-" + df.format(new Date())
				+ ".zip"));
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (!path.endsWith(".zip")) {
				path += ".zip";
			}
			fileLocTextField.setText(path);
		}
	}// GEN-LAST:event_fileLocButtonActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cancelButton;
	private javax.swing.JButton exportButton;
	private javax.swing.JLabel exportPINLabel;
	private javax.swing.JTextField exportPINTextField;
	private javax.swing.JButton fileLocButton;
	private javax.swing.JLabel fileLocLabel;
	private javax.swing.JTextField fileLocTextField;
	private javax.swing.JButton genPINButton;
	private javax.swing.JCheckBox pinToClipboardCheckbox;
	// End of variables declaration//GEN-END:variables
}