const express = require('express');
const app = express();

var path =  require('path');

app.use(express.static(__dirname+'/assets'));

const fs = require('fs');

app.get('/getAllFiles',(req, res) =>{

    var jsonfilename = {};
    jsonfilename.filenames = [];
    fs.readdir('./assets/pdfFiles', (err, pdfFiles) =>{
        pdfFiles.forEach(file => {
            jsonfilename.filenames.push(file);
        });
        res.send(jsonfilename);
    });

});

app.get('/getFileByName', (req,res) =>{
    var fname = req.query.fname;
    console.log(fname);
    fs.readdir('./assets/pdfFiles', (err, pdfFiles) =>{
        var isSuccess = false;
        pdfFiles.forEach(file => {
            if(file == fname){
                isSuccess =  true;
                res.contentType("application/pdf");
                var f = path.join(__dirname,'./assets/pdfFiles/'+file);
                res.download(f);
                //TO OPEN PDF ON BROWSER ONLY 
                //var f = fs.readFileSync('./assets/pdfFiles/'+file);
                //res.send(f);  
            }
        });
        if(!isSuccess){
            res.status(404).send('Cannot find the file');
        }   
    });

});

const port = process.env.PORT || 5000;
app.listen(port,() =>{
    console.log(`Listening on port ${port}`);
});