package com.hongbao.util;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

public class PhotoUtils {
	public static final int PHOTO_REQUEST_GALLERY = 2;// �������ѡ��
	public static final int PHOTO_REQUEST_CUT = 3;// ���
	/**
	 * �����
	 */
	public static void openAlbum(Activity activity) {
		Intent intent = new Intent();
		/* ����Pictures����Type�趨Ϊimage */
		intent.setType("image/*");
		/* ʹ��Intent.ACTION_GET_CONTENT���Action */
		intent.setAction(Intent.ACTION_GET_CONTENT);
		/* ȡ����Ƭ�󷵻ر����� */
		activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}

	/**
	 * �ü�ͼƬ
	 * 
	 * @param activity
	 * @param uri
	 * @param size
	 */
	public static void startPhotoZoom(Activity activity, Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
		intent.putExtra("crop", "true");

		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		// outputX,outputY �Ǽ���ͼƬ�Ŀ��
		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);

		activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}
	
	/**
	 * ����ʡ�ڴ�ķ�ʽ��ȡ������Դ��ͼƬ 
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		Bitmap bitmap=BitmapFactory.decodeStream(is, null, opt);
		if(is!=null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}
}
