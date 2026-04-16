$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
$javaRoot = Join-Path $projectRoot 'src\main\java\com\github\rogerioja89'
$testRoot = Join-Path $projectRoot 'src\test\java\com\github\rogerioja89'

$folders = @(
    (Join-Path $javaRoot 'dto'),
    (Join-Path $javaRoot 'entity'),
    (Join-Path $javaRoot 'repository'),
    (Join-Path $javaRoot 'resource'),
    (Join-Path $javaRoot 'service'),
    $testRoot,
    (Join-Path $projectRoot 'scripts')
)

foreach ($folder in $folders) {
    if (-not (Test-Path $folder)) {
        New-Item -Path $folder -ItemType Directory | Out-Null
        Write-Host "Criado: $folder"
    } else {
        Write-Host "Ja existe: $folder"
    }
}

Write-Host 'Estrutura base pronta.'

