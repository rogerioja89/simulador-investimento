$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
Push-Location $projectRoot

try {
    Write-Host '1) Executando testes...'
    .\mvnw.cmd test

    Write-Host '2) Subindo em modo dev...'
    Write-Host 'A API ficara em http://localhost:8080'
    .\mvnw.cmd quarkus:dev
}
finally {
    Pop-Location
}

