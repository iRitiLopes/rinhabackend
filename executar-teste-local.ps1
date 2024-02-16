# Use este script para executar testes locais

$env:RESULTS_WORKSPACE = "$(Get-Location)\load-test\user-files\results"
$env:GATLING_BIN_DIR = "$(Get-Location)\gatling\bin"
$env:GATLING_WORKSPACE = "$(Get-Location)\load-test\user-files"
$env:GATLING_HOME = "$(Get-Location)\gatling"

Write-Host "RESULTS_WORKSPACE: $env:RESULTS_WORKSPACE"
Write-Host "GATLING_BIN_DIR: $env:GATLING_BIN_DIR"
Write-Host "GATLING_WORKSPACE: $env:GATLING_WORKSPACE"
Write-Host "GATLING_HOME: $env:GATLING_HOME"

function Run-Gatling {
    & "$(Get-Location)\gatling\bin\gatling.bat" -rm local -s RinhaBackendCrebitosSimulation `
        -rd "Rinha de Backend - 2024/Q1: Crébito" `
        -rf "$(Get-Location)\load-test\user-files" `
        -sf "$(Get-Location)\load-test\user-files\simulations"
}

function Start-Test {
    for ($i = 1; $i -le 20; $i++) {
        try {
            # 2 requests to wake the 2 API instances up :)
            Invoke-RestMethod -Uri "http://127.0.0.1:9999/clientes/1/extrato" -ErrorAction Stop
            Write-Host ""
            Invoke-RestMethod -Uri "http://127.0.0.1:9999/clientes/1/extrato" -ErrorAction Stop
            Write-Host ""
            Run-Gatling
            break
        } catch {
            Start-Sleep -Seconds 2
        }
    }
}

Start-Test