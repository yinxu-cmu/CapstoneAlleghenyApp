package edu.cmu.allegheny.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import com.itextpdf.text.DocumentException;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.R.id;
import edu.cmu.allegheny.R.layout;
import edu.cmu.allegheny.util.PDFGenerator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
/**
 * This class is used to capture the signature drawn in touch screen.
 * The default storage path of the signature file is "/mnt/sdcard/download/"
 * @author yinxu
 *
 */
public class CaptureSignatureActivity extends Activity {

	LinearLayout mContent;
	signature mSignature;
	Button mClear, mGetSign, mCancel;
	public static String tempDir;
	public int count = 1;
	public String current = null;
	private Bitmap mBitmap;
	View mView;
	File mypath;

	private String uniqueId;
	private EditText yourName;
	
	private String storeId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signature);
		
		Bundle bundle1 = this.getIntent().getExtras();
		storeId = bundle1.getString("storeId");

		tempDir = Environment.getExternalStorageDirectory() + "/"
				+ getResources().getString(R.string.external_dir) + "/";
		
		Log.d("tempDir", tempDir);
		
		ContextWrapper cw = new ContextWrapper(getApplicationContext());

		/* The default storage path of the signature file is hardcoding here */
		File directory = new File("/mnt/sdcard/download/");

		Log.d("png path", getResources().getString(R.string.external_dir));
		Log.d("directory", directory.getAbsolutePath());
		
		prepareDirectory();
		uniqueId = "report_signature";
		current = uniqueId + ".png";
		mypath = new File(directory, current);

		mContent = (LinearLayout) findViewById(R.id.linearLayout);
		mSignature = new signature(this, null);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mClear = (Button) findViewById(R.id.clear);
		mGetSign = (Button) findViewById(R.id.getsign);
		mGetSign.setEnabled(false);
		mCancel = (Button) findViewById(R.id.cancel);
		mView = mContent;

		yourName = (EditText) findViewById(R.id.yourName);

		mClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.v("log_tag", "Panel Cleared");
				mSignature.clear();
				mGetSign.setEnabled(false);
			}
		});

		mGetSign.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.v("log_tag", "Panel Saved");
				boolean error = captureSignature();
				if (!error) {
					mView.setDrawingCacheEnabled(true);
					mSignature.save(mView);
					Bundle b = new Bundle();
					b.putString("status", "done");
					Intent intent = new Intent();
					intent.putExtras(b);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});

		mCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.v("log_tag", "Panel Canceled");
				Bundle b = new Bundle();
				b.putString("status", "cancel");
				Intent intent = new Intent();
				intent.putExtras(b);
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

	@Override
	protected void onDestroy() {
		Log.w("GetSignature", "onDestory");
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		try {
			Log.d("test", "here");
			String sigPath = "/mnt/sdcard/download/report_signature.png";
			
				Log.d("PDF", "going to gen a signed one");
				PDFGenerator report = new PDFGenerator(storeId, sigPath, getApplicationContext());
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Check if the signature capturing is valid. 
	 * @return true if no error, vice versa.
	 */
	private boolean captureSignature() {

		boolean error = false;
		String errorMessage = "";

		if (yourName.getText().toString().equalsIgnoreCase("")) {
			errorMessage = errorMessage + "Please enter your Name\n";
			error = true;
		}

		if (error) {
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 105, 50);
			toast.show();
		}

		return error;
	}


	/**
	 * Set up the temporary directory which stores temporary file.
	 * @return true if preparation success
	 */
	private boolean prepareDirectory() {
		try {
			if (makedirs()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(
					this,
					"Could not initiate File System.. Is Sdcard mounted properly?",
					1000).show();
			return false;
		}
	}

	/**
	 * Auxiliary function for prepareDirecotry()
	 * @return
	 */
	private boolean makedirs() {
		File tempdir = new File(tempDir);
		if (!tempdir.exists())
			tempdir.mkdirs();

		if (tempdir.isDirectory()) {
			File[] files = tempdir.listFiles();
			for (File file : files) {
				if (!file.delete()) {
					System.out.println("Failed to delete " + file);
				}
			}
		}
		return (tempdir.isDirectory());
	}

	/**
	 * The signature collector. It uses a canvas object to contain the signature
	 * @author 
	 *
	 */
	public class signature extends View {
		private static final float STROKE_WIDTH = 5f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		private Paint paint = new Paint();
		private Path path = new Path();

		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();

		public signature(Context context, AttributeSet attrs) {
			super(context, attrs);
			paint.setAntiAlias(true);
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
		}

		/**
		 * Save the signature as a local PNG file.
		 * @param v is the signature view.
		 */
		public void save(View v) {
			Log.v("log_tag", "Width: " + v.getWidth());
			Log.v("log_tag", "Height: " + v.getHeight());
			if (mBitmap == null) {
				mBitmap = Bitmap.createBitmap(mContent.getWidth(),
						mContent.getHeight(), Bitmap.Config.RGB_565);
				;
			}
			Canvas canvas = new Canvas(mBitmap);
			try {
				FileOutputStream mFileOutStream = new FileOutputStream(mypath);

				v.draw(canvas);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
				mFileOutStream.flush();
				mFileOutStream.close();
				String url = Images.Media.insertImage(getContentResolver(),
						mBitmap, "title", null);
				Log.v("log_tag", "url: " + url);
				// In case you want to delete the file
				// boolean deleted = mypath.delete();
				// Log.v("log_tag","deleted: " + mypath.toString() + deleted);
				// If you want to convert the image to string use base64
				// converter

			} catch (Exception e) {
				Log.v("log_tag", e.toString());
			}
		}

		/**
		 * clear the signature view.
		 */
		public void clear() {
			path.reset();
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(path, paint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			mGetSign.setEnabled(true);

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(eventX, eventY);
				lastTouchX = eventX;
				lastTouchY = eventY;
				return true;

			case MotionEvent.ACTION_MOVE:

			case MotionEvent.ACTION_UP:

				resetDirtyRect(eventX, eventY);
				int historySize = event.getHistorySize();
				for (int i = 0; i < historySize; i++) {
					float historicalX = event.getHistoricalX(i);
					float historicalY = event.getHistoricalY(i);
					expandDirtyRect(historicalX, historicalY);
					path.lineTo(historicalX, historicalY);
				}
				path.lineTo(eventX, eventY);
				break;

			default:
				debug("Ignored touch event: " + event.toString());
				return false;
			}

			invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
					(int) (dirtyRect.top - HALF_STROKE_WIDTH),
					(int) (dirtyRect.right + HALF_STROKE_WIDTH),
					(int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

			lastTouchX = eventX;
			lastTouchY = eventY;

			return true;
		}

		private void debug(String string) {
		}

		/**
		 * Auxiliary function for signature drawing.
		 * @param historicalX
		 * @param historicalY
		 */
		private void expandDirtyRect(float historicalX, float historicalY) {
			if (historicalX < dirtyRect.left) {
				dirtyRect.left = historicalX;
			} else if (historicalX > dirtyRect.right) {
				dirtyRect.right = historicalX;
			}

			if (historicalY < dirtyRect.top) {
				dirtyRect.top = historicalY;
			} else if (historicalY > dirtyRect.bottom) {
				dirtyRect.bottom = historicalY;
			}
		}

		/**
		 * Auxiliary function for signature drawing.
		 * @param eventX
		 * @param eventY
		 */
		private void resetDirtyRect(float eventX, float eventY) {
			dirtyRect.left = Math.min(lastTouchX, eventX);
			dirtyRect.right = Math.max(lastTouchX, eventX);
			dirtyRect.top = Math.min(lastTouchY, eventY);
			dirtyRect.bottom = Math.max(lastTouchY, eventY);
		}
	}
}