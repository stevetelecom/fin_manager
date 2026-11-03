#!/usr/bin/env bash
# Lance l'application Spring Boot.
# Usage : ./run.sh
#         SERVER_PORT=8081 ./run.sh
set -euo pipefail
cd "$(dirname "$0")"
exec ./mvnw spring-boot:run "$@"
