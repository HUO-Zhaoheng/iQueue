param($installPath, $toolsPath, $package, $project)

. (Join-Path $toolsPath common.ps1)

# Determine the file paths
$projectIntelliSenseFilePath = Join-Path $projectScriptsFolderPath $intelliSenseFileName
$origIntelliSenseFilePath = Join-Path $toolsPath $intelliSenseFileName


    if ((Get-Checksum $projectIntelliSenseFilePath) -eq (Get-Checksum $origIntelliSenseFilePath)) {
        # The intellisense file in the project matches the file in the tools folder, delete it
        
        if ($scriptsFolderProjectItem -eq $null) {
            # No Scripts folder
            exit
        }

        try {                    
            $intelliSenseFileProjectItem = $scriptsFolderProjectItem.ProjectItems.Item($intelliSenseFileName)
        }
        catch {
            # The item wasn't found
            exit
        }

        # Delete the project item
        Delete-ProjectItem $intelliSenseFileProjectItem
    }
    else {
        $projectScriptsFolderLeaf = Split-Path $projectScriptsFolderPath -Leaf
        Write-Host "Skipping '$projectScriptsFolderLeaf\$intelliSenseFileName' because it was modified." -ForegroundColor Magenta
    }
