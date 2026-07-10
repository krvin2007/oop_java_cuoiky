$path = "d:\oop_java_cuoiky\QuanLyGara\src\main\java\com\mycompany\quanlygara\view\ConsoleView.java"
$content = Get-Content $path -Raw
$originalContent = $content

# Replace lists
$content = $content -replace 'for\s*\(\s*Customer\s+[a-zA-Z0-9_]+\s*:\s*(owners|availableOwners)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printCustomers($1);'

$content = $content -replace 'for\s*\(\s*Vehicle\s+[a-zA-Z0-9_]+\s*:\s*(vehicles|searchLp|searchName|availableVehicles)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printVehicles($1);'

$content = $content -replace 'for\s*\(\s*Mechanic\s+[a-zA-Z0-9_]+\s*:\s*(mechanicsByName|mechanicsBySalaryAsc|mechanicsBySalaryDesc|availableMechs)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printMechanics($1);'

$content = $content -replace 'for\s*\(\s*LinhKien\s+[a-zA-Z0-9_]+\s*:\s*(partsPriceAsc|partsPriceDesc|partsQtyAsc|partsByName|partsByPrice)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printParts($1);'

$content = $content -replace 'for\s*\(\s*RepairOrder\s+[a-zA-Z0-9_]+\s*:\s*(orders)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printRepairOrders($1);'

$content = $content -replace 'for\s*\(\s*Invoice\s+[a-zA-Z0-9_]+\s*:\s*(invoices)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printInvoices($1);'

$content = $content -replace 'for\s*\(\s*DichVu\s+[a-zA-Z0-9_]+\s*:\s*(list|searchResult)\s*\)\s*\{\s*System\.out\.println\([a-zA-Z0-9_]+(?:\.toString\(\))?\);\s*\}', 'com.mycompany.quanlygara.util.TableFormatter.printServices($1);'

if ($content -ne $originalContent) {
    [System.IO.File]::WriteAllText($path, $content, (New-Object System.Text.UTF8Encoding($False)))
    Write-Host "Updated ConsoleView.java!"
} else {
    Write-Host "No changes were made."
}
