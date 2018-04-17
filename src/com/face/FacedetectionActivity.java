package com.face;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class FacedetectionActivity extends Activity {
	private static final String image="face.jpg";
	private static final int maxface=10;
	//private static Context context;
	private Bitmap Background_image;
	private FaceDetector.Face[] faces;
	private int face_count;
	//ImageView iv;
	private PointF tmp_point=new PointF();
	private Paint tmp_paint=new Paint();
    /** Called when the activity is first created. */
 //   @Override
  //  public void onCreate(Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
     //  setContentView(R.layout.main);
	 //i=(ImageView)findViewById(R.id.i);
        public FacedetectionActivity(Context context){
        	super();
        	updateImage(Environment.getExternalStorageDirectory()+"/"+image);
        	//i.setImageDrawable(("/sdcard/face.jpg"));
        }
	public void updateImage(String image) {
		// TODO Auto-generated method stub
		BitmapFactory.Options bitmapOptions=new BitmapFactory.Options();
		bitmapOptions.inPreferredConfig=Bitmap.Config.RGB_565;
		Background_image=BitmapFactory.decodeFile(image, bitmapOptions);
		FaceDetector face_detector=new FaceDetector(Background_image.getWidth(),Background_image.getHeight(), maxface);
		faces= new FaceDetector.Face[maxface];
		face_count= face_detector.findFaces(Background_image , faces);
		Log.d("face_Detector","face count");
	}
        public void ondraw(Canvas canvas) {
        	canvas.drawBitmap(Background_image,0,0,null);
        	for(int i=0;i<face_count;i++){
        		FaceDetector.Face face=faces[i];
        		tmp_paint.setColor(Color.RED);
        		tmp_paint.setAlpha(100);
        		face.getMidPoint(tmp_point);
        		canvas.drawCircle(tmp_point.x,tmp_point.y,face.eyesDistance(),tmp_paint);
        		
        	}
		}
    }
//}