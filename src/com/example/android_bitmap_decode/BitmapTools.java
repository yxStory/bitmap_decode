package com.example.android_bitmap_decode;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapTools {

	public BitmapTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 位图解码
	 * @param resource   资源文件
	 * @param resId    解码位图的id
	 * @param reqWidth   指定输出位图的宽度
	 * @param reqHeight   指定输出位图的高度
	 * @return
	 */
	public static Bitmap decodeBitmap(Resources resource,int resId,
			int reqWidth,int reqHeight){
		//对位图进行解码的参数设置
		BitmapFactory.Options options = new BitmapFactory.Options();
		//设置inJustDecodeBounds为true，在对位图进行解码的过程中，避免申请内存空间
		//仅仅返回图片的宽和高
		options.inJustDecodeBounds=true;
		//BitmapFactory.decodeResource(resource, resId, options);
		//对图片做一定比例的压缩处理
		options.inSampleSize=calculateInSimpleSize(options, reqWidth,
				reqHeight);
		//设置inJustDecodeBounds为false，真正输出位图
		options.inJustDecodeBounds=false;
		return BitmapFactory.decodeResource(resource, resId, options);
	}
	
	/**
	 * 对图片进行缩放
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSimpleSize(BitmapFactory.Options options,
			int reqWidth,int reqHeight){
		//获得图片的原始宽、高
		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		int inSimpleSize=1;//压缩比例
		if(imageWidth>reqWidth||imageHeight>reqHeight){
			final int widthRatio=Math.round((float)imageWidth
					/(float)reqWidth);
				//Math.round()，四舍五入
			final int heightRatio=Math.round((float)imageHeight
					/(float)reqHeight);
			inSimpleSize=widthRatio>heightRatio?heightRatio:widthRatio;
		}	
		return inSimpleSize;
	}
}
