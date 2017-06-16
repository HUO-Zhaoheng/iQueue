param($installPath, $toolsPath, $package, $project)

. (Join-Path $toolsPath common.ps1)


if ($scriptsFolderProjectItem -eq $null) {
    # No Scripts folder
    Write-Host "No Scripts folder found"
    exit
}

try {    
    Write-Host "Install Avalon success"
}
catch {
    # This will throw if the file already exists, so we need to catch here
    Write-Host "Install Avalon fail"
}
