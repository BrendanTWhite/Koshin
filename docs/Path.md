# Path methods
java.nio.file.Path



## relativize()

`fileRelativePath = customFullPath.relativize(fileFullCustomPath)`

customFullPath is `//server/software/custom`
fileFullCustomPath is `//server/software/custom/SM/PersDtls10.aspx`

fileRelativePath is `SM/PersDtls10.aspx`



## resolve()

`fileFullCustomPath = customFullPath.resolve(fileRelativePath)`

customFullPath is `//server/software/custom`
fileRelativePath is `SM/PersDtls10.aspx`

fileFullCustomPath is `//server/software/custom/SM/PersDtls10.aspx`



## resolveSibling()

`defaultFullPath = customFullPath.resolveSibling(defaultString)`

customFullPath is `//server/software/custom`
defaultString = `'default'`

defaultFullPath is `//server/software/default`



## startsWith()

`if thisFileFullPath.startsWith(customFullPath) then { /* this file is in Custom */ }`

customFullPath is `//server/software/custom`
thisFileFullPath is `//server/software/custom/SM/PersDtls10.aspx`



## toString()

`thisPath.toString()`

Like it says on the tin
