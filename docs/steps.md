# Stages and Statuses


## Stages

There are four stages.

- Custom
- Default
- Distribution
- Manifest


## Custom

For each file in the Custom tree, check if 
an identical equivalent is in Distribution.

If not, copy the Custom file
to overwrite the Distribution file.

Statuses:

- Waiting (not yet started)
- Scan directory (indeterminate)
- Checking each file (with progress bar)
- Finished (done)


## Default

For each file in the Default tree, check if 
an equivalent is in Custom. If so, ignore it.

If not, check if an identical equivalent is in Default. 

If not, copy the Default file 
to overwrite the Distribution file.

Statuses:

- Waiting (not yet started)
- Scan directory (indeterminate)
- Checking each file (with progress bar)
- Finished (done)


## Distribution

For each file in the Distribution tree, check if
an equivalent exists in Default.

If not, check if an equivalent exists in Custom.

If not, delete the file from Distribution.

Statuses:

- Waiting (not yet started)
- Scan directory (indeterminate)
- Checking each file (with progress bar)
- Finished (done)


## Manifest

For each file in the Distribution tree, add a
record to the Manifest file data structure.

Then sort and output the Manifest file.

Statuses:

- Waiting (not yet started)
- Scan directory (indeterminate) (if not already done)
- Checking each file (with progress bar)
- Building manifest (maybe? If microseconds, no separate status needed)
- Finished (done)
