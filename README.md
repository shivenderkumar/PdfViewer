# PdfViewer

Smaple Andriod App Project with sample backend Api's to demonstrate how to downlad pdf file form nodejs server and open it on android app.

### Backend Server

Language : nodejs
pdf files are stored as static resource files in folder(server->assets->pdfFiles)'.
server.js contains two api's :
 -> getAllList 'response array of files names in pdfFiles Folder'
 -> getFileByName 'take filename as reuest and return that pdf file as response'
 
 ### Frontend Android App 
 
 Language : JAVA
 Minimum SDK Level : api 21
 External Depedencies : 
     -> Volley for handilng api calls
     -> bertsc for open pdf in app
 Contains two activity :
     -> MainActivity    : Display file names in ListView by calling 'getAllList' api with SearchView. On itemclick of listview                           PdfViewActivity is called.
     -> PdfViewActivity : get the filename from MainActivity and call 'getFileByName' api.
                          Store response,which is in byte[], in FileOutputStream and create pdf file of in ( ).
                          PdfView from external library is used to open pdf from path.
 
 ###

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
