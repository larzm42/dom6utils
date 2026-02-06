Write-Host "Comparing images..." -ForegroundColor Cyan
Write-Host ""

# Calculate the path to dom6inspector based on current location
$currentPath = (Get-Location).Path
$baseDir = $currentPath

# Find the dom6utils directory
while ($baseDir -and (Split-Path $baseDir -Leaf) -ne "dom6utils") {
    $baseDir = Split-Path $baseDir -Parent
}

if (-not $baseDir) {
    Write-Host "Error: Not in dom6utils directory structure" -ForegroundColor Red
    exit 1
}

# Target is always parallel to dom6utils
$parentDir = Split-Path $baseDir -Parent
$targetBasePath = Join-Path $parentDir "dom6inspector\images\sprites"

Write-Host "Comparing with: $targetBasePath" -ForegroundColor Gray
Write-Host ""
Write-Host "Files that differ:" -ForegroundColor Yellow
Write-Host "==================" -ForegroundColor Yellow

$diffCount = 0
$identicalCount = 0
$errorCount = 0

Get-ChildItem -Filter "*.png" | ForEach-Object {
    $filename = $_.Name
    $targetPath = Join-Path $targetBasePath $filename
    
    # Run magick compare and capture stderr
    $output = & magick compare -metric RMSE $_.FullName $targetPath NULL: 2>&1
    
    # Check if it's an error message
    if ($output -match "^compare:") {
        $errorCount++
    }
    # Check if images are identical (starts with "0 ")
    elseif ($output -match "^0 \(0\)") {
        $identicalCount++
    }
    # Images differ
    else {
        Write-Host $filename
        $diffCount++
    }
}

Write-Host ""
Write-Host "Comparison complete." -ForegroundColor Green
Write-Host "Files that differ: $diffCount" -ForegroundColor Yellow
Write-Host "Identical files: $identicalCount" -ForegroundColor Green
if ($errorCount -gt 0) {
    Write-Host "Errors: $errorCount" -ForegroundColor Red
}
