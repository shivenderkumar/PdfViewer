# PdfViewer

Smaple Andriod App Project with sample backend Api's to demonstrate how to downlad pdf file form nodejs server and open it on android app.

### Backend Server

* used expressjs for backend
* Language : nodejs
server.js contains two api's :
 -> getAllList 'response array of files names in pdfFiles Folder'
 -> getFileByName 'take filename as reuest and return that pdf file as response'
* **NOTE** : pdf files are stored as static resource files in folder(server->assets->pdfFiles) just to demonstrate, you can give file url path from mongodb, which is in the future scope of project.

 ### Frontend Android App 
 
 * Language : JAVA
 * Minimum SDK Level : api 21
 * External Depedencies : 
 ```
 implementation 'com.android.volley:volley:1.1.1'
 implementation 'com.github.barteksc:android-pdf-viewer:3.0.0-beta.5'
 ```
 * Permissions :
 ```
 <uses-permission android:name="android.permission.INTERNET" />
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
 * App Structure:
 
     -> MainActivity    : Display file names in ListView by calling 'getAllList' api with SearchView. On itemclick of listview                           PdfViewActivity is called.
     
     -> PdfViewActivity : get the filename from MainActivity and call 'getFileByName' api.
                          Store response,which is in byte[], in FileOutputStream and create pdf file of in ( ).
                          PdfView from external library is used to open pdf from path.
                          
     -> SingletonRequestQueue Class : to use only one requestQueue object in multiple activities
     
     -> InputStreamVolleyRequest Class
 
 ###

## Getting Started
Smaple Andriod App Project with sample backend Api's to demonstrate how to downlad pdf file form nodejs server and open it on android app.

### Prerequisites

Vistual Studio Code

Android Studio 3.6.3


```
node version v8.10.0
```

### Installing

For Backend, Goto Directory PdfViewerBackend

```
cd PdfViewerBackend
npm install
npm start
```

For Frontend PdfViewerApp, open in Android Studio, and run the app.

## Running the tests

In Development

### Break down into end to end tests

In Development

### And coding style tests

In Development

## Deployment

In Development

## Built With

* [Android Studio] - JAVA
* [Visual Studio Code] - nodejs
* [Postman] - test api's

## Contributing

In Development

## Versioning

Version 1.0 - First Version

## Authors

* **Shivender Kumar** - 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

