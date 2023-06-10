# Scenario 1 - Small handful of Custom file changes

## Custom
Scan    = 1s
Compare = 6s
Process = 1s
Total   = 8s

## Default
Don't run it at all

## Distribution
Don't run it at all

## Grand Total
Total   = 8s


# Scenario 2 - Clean Rebuild

## Custom
Scan    = 1s
Compare = 6s
Process = 1s
Total   = 8s

## Default
Scan    = 1m
Compare = 1.5m
Process = 52m
Total   = 55m

## Distribution
Scan    = 0s
Compare = 0s
Process = 0s
Total   = 0s

## Grand Total 
Total   = 55m


# Scenario 3 - Full Rebuild

## Custom
Scan    = 1s
Compare = 6s
Process = 1s
Total   = 8s

## Default
Scan    = 1m
Compare = 1.5m
Process = 52m
Total   = 55m

## Distribution
Scan    = 1m
Compare = 1.5m
Process = 1s
Total   = 2.5m

## Grand Total 
Total   = 58m
