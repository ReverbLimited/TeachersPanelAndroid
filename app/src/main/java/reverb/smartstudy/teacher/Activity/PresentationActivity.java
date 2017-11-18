package reverb.smartstudy.teacher.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import reverb.smartstudy.teacher.Adapter.FilesAdapter;
import com.example.mdjahirulislam.youtubestyletabs.R;

import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import static java.util.Collections.singletonList;


public class PresentationActivity extends AppCompatActivity {

    private static final String TAG = PresentationActivity.class.getSimpleName();


    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE_RESULT = 1;
    private static int mImageCounter = 0;
    private int GALLERY = 1;
    private int PICK_FILE_REQUEST_CODE = 2;

    private ArrayList<String> imageUrls;
    private String[] mediaPath;
    private String path;


    private Button addPhotoOrFile;
    private TextView pathListTV;
    private LinearLayout m_vwJokeLayout;



    private String filename;
    private Image image;
    private int mMorphCounter1 = 1;
    private ArrayList<String> tempUris;
    private ArrayList<String> imagesUri;

    private boolean samsung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_presentation );
        addPhotoOrFile = (Button) findViewById( R.id.addPhotoOrFile );
        pathListTV = (TextView) findViewById( R.id.pathListTV );
        m_vwJokeLayout = (LinearLayout) findViewById( R.id.lnrImages );
        mediaPath = new String[4];

        imagesUri = new ArrayList<>();
        tempUris = new ArrayList<>();

        // Get runtime permissions if build version >= Android M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE )
                    != PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission( this, Manifest.permission.CAMERA )
                            != PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE )
                            != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA},
                        PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE_RESULT );
            }
        }
        addPhotoOrFile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(PresentationActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA},
                                PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE_RESULT);
                    } else {
                        showPictureDialog();
                    }
                } else {
                    showPictureDialog();
                }

            }
        } );


        Config config = new Config();
        config.setCameraHeight(R.dimen.camera_height);
        config.setToolbarTitleRes(R.string.image_gallery_title);
        config.setSelectionMin(1);
        config.setSelectionLimit(4);
//        config.setSelectedBottomHeight(R.dimen.camera_height);


        ImagePickerActivity.setConfig(config);


        samsung = isSamsung() ;
        Log.d( TAG,String.valueOf( isSamsung() ) );


    }

    public boolean isSamsung()
    {
        String manufacturer = android.os.Build.MANUFACTURER;
        if (manufacturer.toLowerCase( Locale.ENGLISH).contains("samsung"))
            return true;
        else
            return false;
    }


    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select Photo from Gallery",
                "Select Photo from Camera",
                "Select PDF File" };

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                selectGsllery();
                                break;

                            case 1:
                                selectCamera();
                                break;

                            case 2:
                                chooseFileFromGallery();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void selectCamera() {
        Intent intent = new Intent(PresentationActivity.this, ImagePickerActivity.class);

        //add to intent the URIs of the already selected images
        //first they are converted to Uri objects
        ArrayList<Uri> uris = new ArrayList<>(tempUris.size());
        for (String stringUri : tempUris) {
            uris.add(Uri.fromFile(new File(stringUri)));
            Log.d( TAG,stringUri );
        }
        // add them to the intent
        intent.putExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, uris);
        intent.putExtra( "from","camera" );

        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }
    public void selectGsllery() {
        Intent intent = new Intent(PresentationActivity.this, ImagePickerActivity.class);

        //add to intent the URIs of the already selected images
        //first they are converted to Uri objects
        ArrayList<Uri> uris = new ArrayList<>(tempUris.size());
        for (String stringUri : tempUris) {
            uris.add(Uri.fromFile(new File(stringUri)));
            Log.d( TAG,stringUri );
        }
        // add them to the intent
        intent.putExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, uris);
        intent.putExtra( "from","gallery" );

        startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }


    public void chooseFileFromGallery() {

        if (samsung){

            Intent intent1 = new Intent("com.sec.android.app.myfiles.PICK_DATA");
            intent1.putExtra("CONTENT_TYPE", "application/pdf");
            intent1.addCategory(Intent.CATEGORY_DEFAULT);
            startActivityForResult(intent1, PICK_FILE_REQUEST_CODE);

        }else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        }


    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == INTENT_REQUEST_GET_IMAGES && resultCode == Activity.RESULT_OK) {

            tempUris.clear();

            ArrayList<Uri> imageUris = data.getParcelableArrayListExtra( ImagePickerActivity.EXTRA_IMAGE_URIS );
            for (int i = 0; i < imageUris.size(); i++) {
                tempUris.add( imageUris.get( i ).getPath() );
                Log.d( TAG,"onActivityResult =====> "+tempUris.get( i ) );
            }
            Toast.makeText( PresentationActivity.this, "Image Added", Toast.LENGTH_LONG ).show();
            createPdf();
        }else if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            Uri uri = data.getData();
            path = getPathFromURI( PresentationActivity.this,uri );
            pathListTV.setVisibility( View.VISIBLE );
            pathListTV.setText( path );

        }
    }




    public static String getPathFromURI(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }



    // Create Pdf of selected images
    void createPdf() {
        if (imagesUri.size() == 0) {
            if (tempUris.size() == 0) {
                Toast.makeText(PresentationActivity.this, "No Image Selected", Toast.LENGTH_LONG).show();
                return;
            } else {
                imagesUri = (ArrayList<String>) tempUris.clone();
            }
        }
        new MaterialDialog.Builder(PresentationActivity.this)
                .title(R.string.creating_pdf)
                .content(R.string.enter_file_name)
                .input(getString(R.string.example), null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (input == null || input.toString().trim().equals("")) {
                            Toast.makeText(PresentationActivity.this, R.string.toast_name_not_blank, Toast.LENGTH_LONG).show();
                        } else {
                            filename = input.toString();

                            new CreatingPdf().execute();

                            if (mMorphCounter1 == 0) {
                                mMorphCounter1++;
                            }
                        }
                    }
                })
                .show();
    }

    void openPdf() {
        File file = new File(path);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file), getString(R.string.pdf_type));
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, getString(R.string.open_file));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(PresentationActivity.this, R.string.toast_no_pdf_app, Toast.LENGTH_LONG).show();
        }
    }

    public void clickToOpenPdf(View view) {
        openPdf();
    }


    public class CreatingPdf extends AsyncTask<String, String, String> {

        // Progress dialog
        MaterialDialog.Builder builder = new MaterialDialog.Builder(PresentationActivity.this)
                .title(R.string.please_wait)
                .content(R.string.populating_list)
                .cancelable(false)
                .progress(true, 0);
        MaterialDialog dialog = builder.build();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + PresentationActivity.this.getString(R.string.pdf_dir);

            File folder = new File(path);
            if (!folder.exists()) {
                boolean success = folder.mkdir();
                if (!success) {
                    Toast.makeText(PresentationActivity.this, "Error on creating application folder", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

            path = path + filename + PresentationActivity.this.getString(R.string.pdf_ext);

            Log.v("stage 1", "store the pdf in sd card");

            Document document = new Document( PageSize.A4, 38, 38, 50, 38);

            Log.v("stage 2", "Document Created");

            Rectangle documentRect = document.getPageSize();

            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));

                Log.v("Stage 3", "Pdf writer");

                document.open();

                // User Define Method

                try {

                    // get input stream
                    PdfContentByte canvas = writer.getDirectContentUnder();
                    InputStream ims = getAssets().open("final.png");
                    Bitmap bmp = BitmapFactory.decodeStream(ims);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Image image = Image.getInstance(stream.toByteArray());
//                    image.scaleAbsolut
                    image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
                    image.setAlignment(Element.ALIGN_LEFT);
                    document.add(image);
//            document.close();

                }
                catch(IOException ex)
                {
                    return null;
                }

//                addMetaData(document);
                addTitlePage(document,writer);




                Log.v("Stage 4", "Document opened");

                for (int i = 0; i < imagesUri.size(); i++) {

                    document.newPage();

                    Bitmap bmp = MediaStore.Images.Media.getBitmap(
                            PresentationActivity.this.getContentResolver(), Uri.fromFile(new File(imagesUri.get(i))));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);


                    image = Image.getInstance(imagesUri.get(i));


                    if (bmp.getWidth() > documentRect.getWidth()
                            || bmp.getHeight() > documentRect.getHeight()) {
                        //bitmap is larger than page,so set bitmap's size similar to the whole page
                        image.scaleAbsolute(documentRect.getWidth(), documentRect.getHeight());
                    } else {
                        //bitmap is smaller than page, so add bitmap simply.
                        //[note: if you want to fill page by stretching image,
                        // you may set size similar to page as above]
                        image.scaleAbsolute(bmp.getWidth(), bmp.getHeight());
                    }

                    Log.v("Stage 6", "Image path adding");

                    image.setAbsolutePosition(
                            (documentRect.getWidth() - image.getScaledWidth()) / 2,
                            (documentRect.getHeight() - image.getScaledHeight()) / 2);
                    Log.v("Stage 7", "Image Alignments");

//                    createBackgroundPdf( path,imagesUri.get( 0 ) );
                    image.setBorder(Image.BOX);

                    image.setBorderWidth(15);

                    document.add(image);


                }

                Log.v("Stage 8", "Image adding");

                document.close();

                Log.v("Stage 9", "Document Closed" + path);
            } catch (Exception e) {
                e.printStackTrace();
            }

            document.close();
            imagesUri.clear();
            tempUris.clear();
            mImageCounter = 0;

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            openPdf.setVisibility(View.VISIBLE);
            Snackbar.make(PresentationActivity.this.findViewById(android.R.id.content)
                    , R.string.snackbar_pdfCreated
                    , Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_viewAction, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<File> list = new ArrayList<>(singletonList(new File(path)));
                            FilesAdapter filesAdapter = new FilesAdapter(PresentationActivity.this, list);
                            filesAdapter.openFile(path);
                        }
                    }).show();
            dialog.dismiss();
            pathListTV.setVisibility( View.VISIBLE );
            pathListTV.setText( path );
//            morphToSuccess(createPdf);
        }
    }

    // Set PDF document Properties
    public void addMetaData(Document document)

    {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal,	Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    public void addTitlePage(Document document,PdfWriter writer) throws DocumentException {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph

        prHead.setPaddingTop( 10.0f );
        prHead.add("[School Name]\n");

        // Create Table into Document with 1 Row
        PdfPTable myTable = new PdfPTable(1);
        // 100.0f mean width of table is same as Document size
        myTable.setWidthPercentage(100.0f);

        // Create New Cell into Table
        PdfPCell myCell = new PdfPCell(new Paragraph(""));
        myCell.setBorder(Rectangle.BOTTOM);

        // Add Cell into Table
        myTable.addCell(myCell);

        prHead.setFont(catFont);
        prHead.add("\n[Teacher Name]\n\n");
        prHead.setAlignment( Element.ALIGN_CENTER);


        // Add all above details into Document

        document.add(prHead);
//        document.add(myTable);

        document.add(myTable);

        // Now Start another New Paragraph
        Paragraph prPersinalInfo = new Paragraph();
        prPersinalInfo.setFont(smallBold);
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("[Subject Name]\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("[Date:08/11/2017]\n");
        prPersinalInfo.add("\n");
        prPersinalInfo.add("City: Dhaka. State: CA\n");
        prPersinalInfo.add("Country: Dhaka Zip Code: 000001\n36 Green Road, Kalabagan, Dhaka – 1205\n" +
                "info@reverb.com.bd www.reverb.com.bd\n\n");
        prPersinalInfo
                .add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n\n");

        prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

        document.add(prPersinalInfo);
        document.add(myTable);

//		document.add(myTable);

        Paragraph prProfile = new Paragraph();
        prProfile.setFont(smallBold);
        prProfile.add("\n \n Profile : \n ");
        prProfile.setFont(normal);
        prProfile.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");

        prProfile.setFont(smallBold);
        document.add(prProfile);

        // Create new Page in PDF
//        document.newPage();



    }



    public void createBackgroundPdf(String dest,String IMAGE) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Berlin!"));
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance(IMAGE);
        image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
        document.close();
    }

}
