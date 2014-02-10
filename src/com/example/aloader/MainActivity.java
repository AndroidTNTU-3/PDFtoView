package com.example.aloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;

public class MainActivity extends Activity {
	
	String path = "http://www.ex.ua/load/93706495/java.pdf";
	File filen;
	String filePath = "";
	ProgressDialog progressDialog;
	TextView textload;
	Log log;
	Button button_view;
	Button button_download;
	MyTask task;
	
	String fileName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button_view = (Button) findViewById(R.id.button_view);
        button_download = (Button) findViewById(R.id.button_download);
        textload = (TextView) findViewById(R.id.textViewload);
        
        button_view.setOnClickListener(new ButtonListener());
        button_download.setOnClickListener(new ButtonListener());
        progressDialog = new ProgressDialog(this);
        
        filen = new File(path);
        fileName = filen.getName();
        
        task = new MyTask();
        
    }


	public class MyTask extends AsyncTask<String, Integer, File>{
    	URL url;
        HttpURLConnection urlConnection;
        InputStream inputStream;
        FileOutputStream foStream;  
        int downloadedSize;
        int totalSize;
        
        protected void onPreExecute(){
        	progressDialog.setMessage("Download file ...");
            progressDialog.setCancelable(false);
            progressDialog.setMax(100);
            progressDialog
              .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         
            progressDialog.show();
        }
        
		@Override
		protected File doInBackground(String... params) {
	        byte[] buffer = new byte[512];
	        
	        File file = null;
	        inputStream = null;
	        int bufflenght = 0;
	        totalSize = 0;
	        
	        try {
				url = new URL(params[0]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
	        
			try {
				
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();
				
				 if (!Environment.getExternalStorageState().equals(
					        Environment.MEDIA_MOUNTED)) {
					      Log.i("asd", "SD-card not accessible: " + Environment.getExternalStorageState());
					 }
				File sdPath = Environment.getExternalStorageDirectory();

			    sdPath = new File(sdPath.getAbsolutePath() + "/cache");
			    sdPath.mkdirs();
			    file = new File(sdPath, fileName); // fileName = java.pdf
			    filePath = Environment.getExternalStorageDirectory().toString() + "/cache/" + fileName;
				
				inputStream = urlConnection.getInputStream();
				
				foStream = new FileOutputStream(file);

				downloadedSize = 0;
				totalSize = urlConnection.getContentLength();

				
				while((bufflenght = inputStream.read(buffer)) > 0 ){
					
					foStream.write(buffer, 0, bufflenght);
					downloadedSize += bufflenght;
					publishProgress(downloadedSize, totalSize);
					
				}

				foStream.close();
				inputStream.close();
				return file;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onProgressUpdate(Integer... values){
			 super.onProgressUpdate(values);
		
				 int percent = ((values[0] * 100) / values[1]);
				 String format = String.format("%1dKb/%2dKb", values[0]/1024, values[1]/1024);
				 progressDialog.setProgress(percent);
				 progressDialog.setProgressNumberFormat(format);

		} 
		
		protected void onPostExecute(File file) {
			
			super.onPostExecute(file);
			progressDialog.hide();
			textload.setText("File" + " \"" + fileName + "\" " + "is loaded" + "\n" +  "total size: " + totalSize/1024 + " Kb");
			
		}
    	
    }
	
	private class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			Button but = (Button) v;
			
			switch (but.getId()){
				case R.id.button_view:
					Intent intent = new Intent(getApplicationContext(), ViewerActivity.class);
					intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, filePath);
					startActivity(intent);
					break;
				case R.id.button_download:
					task.execute(path);
					break;		
				default:
				break;
			}
			
		}
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
