package com.example.lian.xiangmu_kuangjia.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lian.xiangmu_kuangjia.FaBuActivity;
import com.example.lian.xiangmu_kuangjia.R;
import com.example.lian.xiangmu_kuangjia.pojo.Constent;
import com.example.lian.xiangmu_kuangjia.pojo.MyInformation;
import com.example.lian.xiangmu_kuangjia.pojo.Sport_name;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_mine extends Fragment implements View.OnClickListener {
    private RelativeLayout frame_my_information;
    private ImageView imageView;
    final List<MyInformation.Myself> myselfs = new ArrayList<MyInformation.Myself>();
  final   File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/"+
            getPhotoFileName());
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private TextView fabu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);
        frame_my_information = ((RelativeLayout) view.findViewById(R.id.frame_my_information));

        getMyinformation();

        imageView = ((ImageView) view.findViewById(R.id.imageView));
        fabu = ((TextView) view.findViewById(R.id.fabuhuodong));

        fabu.setOnClickListener(this);
        imageView.setOnClickListener(this);
        frame_my_information.setOnClickListener(this);

        return view;

    }

    private void getMyinformation() {
        RequestParams paramas = new RequestParams(Constent.URL+"/ww/getMyInformation?name="+Sport_name.NAME+"");

        x.http().get(paramas, new Callback.CacheCallback<String>() {
            private TextView xingming;
            private TextView jifen;
            private TextView fensi;
            private TextView zhan;

            @Override
            public void onSuccess(String result) {

                Gson g = new Gson();
                MyInformation information = g.fromJson(result, MyInformation.class);

                myselfs.addAll(information.myself);

                zhan = ((TextView) getView().findViewById(R.id.guanzhu));
                fensi = ((TextView) getView().findViewById(R.id.fensi));
                jifen = ((TextView) getView().findViewById(R.id.jifen));
                xingming = ((TextView) getView().findViewById(R.id.xingming));

                xingming.setText(Sport_name.NAME);
                zhan.setText(myselfs.get(0).likeNum.toString());
                fensi.setText(myselfs.get(0).fansNum.toString());
                jifen.setText(myselfs.get(0).followNum.toString());
                x.image().bind(imageView, Constent.URL+"ww/imgs/"+myselfs.get(0).headId+"");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        return sdf.format(date) + ".png";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.frame_my_information:
//                popupdown1();
//            break;
            case R.id.imageView:
                popupdown2();
                break;
            case R.id.fabuhuodong:
                fabu();
                break;
        }
    }

    private void fabu() {
        System.out.println("123");
        Intent intent = new Intent(getActivity(), FaBuActivity.class);
        startActivity(intent);
    }


    private void popupdown2() {
        AlertDialog.Builder dialog  = new AlertDialog.Builder(getActivity());
        dialog.setNegativeButton("拍 照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromCamera();
            }
        });

        dialog.setPositiveButton("选择照片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getPicFromPhoto();
            }
        });
        dialog.show();
    }
    private void popupdown1() {
        AlertDialog.Builder dialog  = new AlertDialog.Builder(getActivity());
        dialog.setNegativeButton("选择图片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getPicFromCamera();
            }
        });
        dialog.show();
    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
//        System.out.println("getPicFromCamera==========="+file.getAbsolutePath());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CAMERA_REQUEST);

    }

    private void getPicFromPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);

    }

    private void send() {


        Toast.makeText(getActivity(),Sport_name.NAME,Toast.LENGTH_LONG).show();
        RequestParams params = new RequestParams(Constent.URL+"ww/imag?name="+Sport_name.NAME+"");//ww 是你要访问的servlet

        params.addBodyParameter("fileName",file.getName());
        params.addBodyParameter("file",file);
//        params.addBodyParameter("file",file1);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功  固定
                        System.out.println("CAMERA_REQUEST"+file.getAbsolutePath());
                        if (file.exists()) {
                            photoClip(Uri.fromFile(file));
                        }
                        break;
                    default:
                        break;
                }
                break;
            case PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        saveImageToGallery(getActivity(),photo);//保存bitmap到本地
                        imageView.setImageBitmap(photo);
                    }
                }
                break;
            default:
                break;
        }
    }
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }
    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = System.currentTimeMillis() + ".jpg";
//        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
            send();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

    }

}