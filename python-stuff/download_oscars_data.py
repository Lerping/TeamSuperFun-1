from datapackage import Package

oscar_data = []

package = Package('https://datahub.io/rufuspollock/oscars-nominees-and-winners/datapackage.json')

# print list of all resources:
print(package.resource_names)

# print processed tabular data (if exists any)
for resource in package.resources:
    if resource.descriptor['datahub']['type'] == 'derived/csv':
        oscar_data = resource.read()

for item in oscar_data:
    print(item)