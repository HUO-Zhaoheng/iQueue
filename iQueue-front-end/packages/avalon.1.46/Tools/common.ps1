function Get-Checksum($file) {
    $cryptoProvider = New-Object "System.Security.Cryptography.MD5CryptoServiceProvider"
	
    $fileInfo = Get-Item $file
	trap { ;
	continue } $stream = $fileInfo.OpenRead()
    if ($? -eq $false) {
		# Couldn't open file for reading
        return $null
	}
    
    $bytes = $cryptoProvider.ComputeHash($stream)
    $checksum = ''
	foreach ($byte in $bytes) {
		$checksum += $byte.ToString('x2')
	}
    
	$stream.Close() | Out-Null
    
    return $checksum
}


function Delete-ProjectItem($item) {
    $itemDeleted = $false
    for ($1=1; $i -le 5; $i++) {
        try {
            $item.Delete()
            $itemDeleted = $true
            break
        }
        catch {
            # Try again in 200ms
            [System.Threading.Thread]::Sleep(200)
        }
    }
    if ($itemDeleted -eq $false) {
        throw "Unable to delete project item after five attempts."
    }
}

# Extract the version number from the jquery file in the package's content\scripts folder
$packageScriptsFolder = Join-Path $installPath Content\Scripts
$jqueryFileName = Join-Path $packageScriptsFolder "avalon.*.js" | Get-ChildItem -Exclude "*.min.js" | Split-Path -Leaf

# Get the project item for the scripts folder
try {
    $scriptsFolderProjectItem = $project.ProjectItems.Item("Scripts")
    $projectScriptsFolderPath = $scriptsFolderProjectItem.FileNames(1)
}
catch {
    # No Scripts folder
    Write-Host "No scripts folder found"
}