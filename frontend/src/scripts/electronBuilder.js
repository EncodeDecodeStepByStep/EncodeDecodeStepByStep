const fs = require('fs');
const finalPath = './public/electron.js'

const args = process.argv.slice(2).reduce((acc, arg) => {
    let [k, v = true] = arg.split('=')
    acc[k] = v
    return acc
}, {})

let copyPath ='';

if(args.isDev){
    copyPath = './src/scripts/electronFiles/electronForDev.js'
}else{
    copyPath = './src/scripts/electronFiles/electronForBuild.js'
}

const content = fs.readFileSync(copyPath, {encoding:'utf8', flag:'r'}); 
fs.writeFileSync(finalPath,content,{encoding:'utf8',flag:'w'})

