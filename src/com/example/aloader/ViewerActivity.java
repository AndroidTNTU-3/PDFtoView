package com.example.aloader;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;

public class ViewerActivity extends PdfViewerActivity {
	
	public static final String DATABASE_NAME = "rssreader";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_viewer);
		LayoutInflater ltInflater = getLayoutInflater();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public int getNextPageImageResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPageNumberEditField() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPageNumberResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPasswordEditField() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPasswordExitButton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPasswordLayoutResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPdfPasswordOkButton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPreviousPageImageResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getZoomInImageResource() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getZoomOutImageResource() {
		// TODO Auto-generated method stub
		return 0;
	}

}
