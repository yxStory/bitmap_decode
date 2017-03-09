package com.example.android_bitmap_decode;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapTools {

	public BitmapTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * λͼ����
	 * @param resource   ��Դ�ļ�
	 * @param resId    ����λͼ��id
	 * @param reqWidth   ָ�����λͼ�Ŀ��
	 * @param reqHeight   ָ�����λͼ�ĸ߶�
	 * @return
	 */
	public static Bitmap decodeBitmap(Resources resource,int resId,
			int reqWidth,int reqHeight){
		//��λͼ���н���Ĳ�������
		BitmapFactory.Options options = new BitmapFactory.Options();
		//����inJustDecodeBoundsΪtrue���ڶ�λͼ���н���Ĺ����У����������ڴ�ռ�
		//��������ͼƬ�Ŀ�͸�
		options.inJustDecodeBounds=true;
		//BitmapFactory.decodeResource(resource, resId, options);
		//��ͼƬ��һ��������ѹ������
		options.inSampleSize=calculateInSimpleSize(options, reqWidth,
				reqHeight);
		//����inJustDecodeBoundsΪfalse���������λͼ
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(resource, resId, options);
	}
	
	/**
	 * ��ͼƬ��������
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSimpleSize(BitmapFactory.Options options,
			int reqWidth,int reqHeight){
		//���ͼƬ��ԭʼ����
		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		int inSimpleSize=1;//ѹ������
		if(imageWidth>reqWidth||imageHeight>reqHeight){
			final int widthRatio=Math.round((float)imageWidth
					/(float)reqWidth);
				//Math.round()����������
			final int heightRatio=Math.round((float)imageHeight
					/(float)reqHeight);
			inSimpleSize=widthRatio>heightRatio?heightRatio:widthRatio;
		}	
		return inSimpleSize;
	}
}
